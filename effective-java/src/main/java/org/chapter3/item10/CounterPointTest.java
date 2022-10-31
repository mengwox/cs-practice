package org.chapter3.item10;

import java.util.Set;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
public class CounterPointTest {
    private static final Set<Point> unitCircle = Set.of(
            new Point(1, 0), new Point(-1, 0),
            new Point(0, 1), new Point(0, -1)
    );

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }

    public static void main(String[] args) {
        Point p = new Point(1, 0);
        System.out.println(onUnitCircle(p));
    }
}
