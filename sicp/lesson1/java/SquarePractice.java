package lesson1.java;

/**
 * 求一个数的平方根
 * 三种写法:
 * 1.while true break
 * 2.普通递归
 * 3.线性递归
 *
 * @author mawenhao 2022/7/2
 */
public class SquarePractice {
    /**
     * 允许的误差范围
     */
    private static double LES = 1e-6;

    public static void main(String[] args) {
        Double init = 1D;
        Double num = 18D;
        System.out.println(square(num, init));
    }

    /**
     * 求平方根
     *
     * @param num
     * @param result
     * @return num的平方根
     */
    private static double square(Double num, Double result) {
        double square = num / result;
        double les;
        if (square >= result) {
            les = square - result;
        } else {
            les = result - square;
        }
        if (les < LES) {
            return result;
        } else {
            result = (square + result) / 2D;
            return square(num, result);
        }
    }
}
