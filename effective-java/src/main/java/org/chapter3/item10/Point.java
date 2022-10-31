package org.chapter3.item10;

import lombok.AllArgsConstructor;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
@AllArgsConstructor
public class Point {
    private final int x;
    private final int y;

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Point)) {
//            return false;
//        }
//        Point point = (Point) obj;
//        return x == point.x && y == point.y;
//    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    public static void main(String[] args) {
        Point x = new Point(1, 1);
        Point y = new Point(1, 1);
        Point z = new Point(1, 1);
        System.out.println(x.equals(y));
        System.out.println(y.equals(z));
        System.out.println(x.equals(z));
    }
}
