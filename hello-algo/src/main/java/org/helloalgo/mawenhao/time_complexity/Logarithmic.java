package org.helloalgo.mawenhao.time_complexity;

/**
 * 复杂度分析:对数阶logN
 * 场景:二分查找, 分治算法
 *
 * @author mawenhao 2023/1/10
 */
public class Logarithmic {
    public static void main(String[] args) {
        System.out.println(logarithmic(1024));
        System.out.println(logarithmic(1048576));
    }

    static int logarithmic(int n) {
        int count = 0;
        while (n > 1) {
            n = n / 2;
            count++;
        }
        return count;
    }
}
