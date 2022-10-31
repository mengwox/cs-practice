package org.chapter3.item10;

import org.chapter3.item10.enums.Color;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        if (!(obj instanceof ColorPoint colorPoint)) {
            return obj.equals(this);
        }
        return super.equals(obj) && color == colorPoint.color;
    }

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        ColorPoint colorPoint = new ColorPoint(1, 2, Color.RED);
        System.out.println(point.equals(colorPoint));
        System.out.println(colorPoint.equals(point));
    }
}
