package lesson2.java;

/**
 * 斐波那契数列java实现
 *
 * @author mawenhao 2022/7/8
 */
public class FibonacciPractice {
    public static void main(String[] args) {
        System.out.print(fibonacci(0) + ", ");
        System.out.print(fibonacci(1) + ", ");
        System.out.print(fibonacci(2) + ", ");
        System.out.print(fibonacci(3) + ", ");
        System.out.print(fibonacci(4) + ", ");
        System.out.print(fibonacci(5) + ", ");
        System.out.print(fibonacci(6) + ", ");
        System.out.print(fibonacci(7) + ", ");
        System.out.print(fibonacci(8) + ", ");
        System.out.print(fibonacci(9) + ", ");
        System.out.print(fibonacci(10) + "\n");
    }

    /**
     * 计算斐波那契数列第index项的值
     *
     * @param index 索引
     * @return 数列第index项的值
     */
    private static int fibonacci(int index) {
        if (index < 2) {
            return index;
        }
        return fibonacci(index, 2, 1, 0);
    }

    /**
     * 斐波那契数列计算第index项的值, 迭代模型实现.
     *
     * @param index   索引
     * @param cursor  游标
     * @param prefix1 数列第cursor-1项的值
     * @param prefix2 数列第cursor-2项的值
     * @return 数列第index项的值
     */
    private static int fibonacci(int index, int cursor, int prefix1, int prefix2) {
        if (index < cursor) {
            return prefix1;
        } else if (index == cursor) {
            return prefix1 + prefix2;
        }
        //这里用cursor++会无限循环,因为传入下一个fib函数的cursor还是当前值.所以要用++cursor
        return fibonacci(index, ++cursor, (prefix1 + prefix2), prefix1);
    }
}