package edu.byu.fractal;

import android.graphics.Point;

/**
 * Created by Jessica Erickson on 12/5/2015.
 */
public class TriangleStuff {
    public TriangleStuff(Point point, double angle, double length) {
        this.point = point;
        this.angle = angle;
        this.length = length;
    }

    Point point;
    double angle;
    double length;
}
