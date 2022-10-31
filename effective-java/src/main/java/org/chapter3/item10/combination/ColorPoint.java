package org.chapter3.item10.combination;

import org.chapter3.item10.Point;
import org.chapter3.item10.enums.Color;

import java.util.Arrays;
import java.util.Objects;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint cp)) {
            return false;
        }
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
