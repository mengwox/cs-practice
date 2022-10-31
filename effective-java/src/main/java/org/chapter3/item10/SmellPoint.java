package org.chapter3.item10;

import org.chapter3.item10.enums.Color;
import org.chapter3.item10.enums.Smell;

/**
 * TODO
 *
 * @author mawenhao 2022/10/31
 */
public class SmellPoint extends Point {
    private final Smell smell;

    public SmellPoint(int x, int y, Smell smell) {
        super(x, y);
        this.smell = smell;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        if (!(obj instanceof SmellPoint smellPoint)) {
            return obj.equals(this);
        }
        return super.equals(obj) && smell == smellPoint.smell;
    }

    public static void main(String[] args) {
        SmellPoint smellPoint = new SmellPoint(1, 2, Smell.BLUE);
        ColorPoint colorPoint = new ColorPoint(1, 2, Color.BLUE);
        System.out.println(colorPoint.equals(smellPoint));
    }
}
