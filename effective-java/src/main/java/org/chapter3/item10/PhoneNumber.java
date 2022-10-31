package org.chapter3.item10;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
public final class PhoneNumber {
    private final short areaCode, prefix, lineNum;

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
}
