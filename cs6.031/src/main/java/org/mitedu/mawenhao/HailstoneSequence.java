package org.mitedu.mawenhao;

/**
 * <p>
 * As a running example, we’re going to explore the hailstone sequence, which is
 * defined as follows. Starting with a number n, the next number in the sequence is n/2
 * if n is even, or 3n+1 if n is odd. The sequence ends when it reaches 1.
 * </p>
 * Here are some examples:
 * <p>
 * 2, 1<br/>
 * 3, 10, 5, 16, 8, 4, 2, 1<br/>
 * 4, 2, 1<br/>
 * 2n, 2n-1 , ... , 4, 2, 1<br/>
 * 5, 16, 8, 4, 2, 1<br/>
 * 7, 22, 11, 34, 17, 52, 6, 13, 40, ...? (where does this stop?)
 * </p>
 *
 * @author mawenhao 2022/12/3
 */
public class HailstoneSequence {
    public static void main(String[] args) {
        int n = 3;
        while (n != 1) {
            System.out.print(n + ",");
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        System.out.println(n);
    }
}
