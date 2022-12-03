package org.chapter3.item10;

/**
 * 11.重写equals方法时,一定也要重写hashCode方法
 *
 * @author mawenhao 2022/10/31
 */
public final class PhoneNumber implements Cloneable {
    private final short areaCode, prefix, lineNum;
    /**
     * 缓存hashCode值
     */
    private int hashCode;

    public PhoneNumber(short areaCode, short prefix, short lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "area code");
        this.lineNum = rangeCheck(lineNum, 999, "area code");
    }

    private short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PhoneNumber pn)) {
            return false;
        }
        pn = (PhoneNumber) obj;
        return pn.areaCode == areaCode && pn.prefix == prefix && pn.lineNum == lineNum;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            //懒加载
            result = Short.hashCode(areaCode);
            result = result * 31 + Short.hashCode(prefix);
            result = result * 31 + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }

    @Override
    protected PhoneNumber clone(){
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    //测试
    public static void main(String[] args) {
        PhoneNumber num1 = new PhoneNumber((short) 12, (short) 13, (short) 14);
        PhoneNumber num2 = new PhoneNumber((short) 12, (short) 13, (short) 14);
        System.out.println(num1.hashCode());
        System.out.println(num2.hashCode());
    }
}
