package org.mawenhao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Foo entity
 *
 * @author mawenhao 2023/5/3
 */
@Data
@AllArgsConstructor
public class Foo {
    private Integer index;
    private Integer height;

    public static List<Foo> getFooList() {
        val list = new ArrayList<Foo>();
        int size = 1000;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(new Foo(i, random.nextInt(1000)));
        }
        return list;
    }

    public static void main(String[] args) {
        List<Foo> collection = getFooList();
        collection.stream()
                .map(Foo::getHeight)
                .filter(height -> height % 2 == 0)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
    }
}
