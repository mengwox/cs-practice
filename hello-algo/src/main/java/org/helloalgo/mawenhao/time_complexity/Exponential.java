package org.helloalgo.mawenhao.time_complexity;

/**
 * 复杂度分析:指数阶2的n次方实现
 * 类似场景:细胞分裂
 *
 * @author mawenhao 2023/1/10
 */
public class Exponential {
    public static void main(String[] args) {
        System.out.println("操作次数:" + exponential(20));
        System.out.println("操作次数:" + exponential(10));
    }

    static long exponential(long n) {
        long count = 0, base = 1;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < base; j++) {
                count++;
            }
            base *= 2;
        }
        System.out.println("base值:" + base);
        return count;
    }
}
