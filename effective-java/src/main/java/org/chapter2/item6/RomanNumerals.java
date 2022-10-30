package org.chapter2.item6;

import java.util.regex.Pattern;

/**
 * 6.避免创建不必要的对象
 *
 * @author mawenhao 2022/10/28
 */
public class RomanNumerals {
    /**匹配有效的罗马数字*/
    private static final String REGEX = "^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";

    private static final Pattern ROMAN = Pattern.compile(REGEX);

    static boolean isRomanNumeralSlow(String str) {
        return str.matches(REGEX);
    }

    static boolean isRomanNumeralFast(String str) {
        return ROMAN.matcher(str).matches();
    }

    public static void main(String[] args) {
        int numSets = 10;
        int numReps = 10;
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < numReps; j++) {
                // Change Slow to Fast to see performance difference
                b ^= isRomanNumeralFast("MCMLXXVI");
            }
            long end = System.nanoTime();
            System.out.println(((end - start) / (1_000. * numReps)) + " μs.");
        }

        // Prevents VM from optimizing away everything.
        if (!b)
            System.out.println();
    }
}
