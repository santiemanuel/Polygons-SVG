package com.poly.shapes.model;

import java.util.HashMap;

public abstract class PointGeneric {

    private Double x;
    private Double y;

    public PointGeneric(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    //get distance
    public HashMap<Integer, Double> getDistance(PointGeneric p) {
        HashMap<Integer, Double> distances = new HashMap<>();

        distances.put(0, Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2)));
        return distances;
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}
