package com.poly.shapes.model;

public class Point extends PointGeneric {
    
    public Point(double x, double y) {
        super(x, y);
    }
    
    public static Point at(double x, double y) {
        return new Point(x, y);
    }
}
