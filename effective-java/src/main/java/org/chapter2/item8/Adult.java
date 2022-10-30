package org.chapter2.item8;

/**
 * TODO
 *
 * @author mawenhao 2022/10/29
 */
public class Adult {
    public static void main(String[] args) throws Exception {
        try (Room room = new Room(8)){
            System.out.println("goodbye");
        }
    }
}
