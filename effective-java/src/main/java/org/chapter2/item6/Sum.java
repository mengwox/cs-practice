package org.chapter2.item6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 要优先使用基本类型,而不是装箱基本类型, 要当心无意识的自动装箱
 *
 * @author mawenhao 2022/10/28
 */
public class Sum {
    public static void main(String[] args) {
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
        Map<String,String> map = new HashMap<>();
        Set<String> strings = map.keySet();
    }
}
