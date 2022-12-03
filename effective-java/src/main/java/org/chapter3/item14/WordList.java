package org.chapter3.item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * 14. 考虑实现Comparable接口
 *
 * @author mawenhao 2022/11/18
 */
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);

    }
}
