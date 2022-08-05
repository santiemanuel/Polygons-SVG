package com.poly.shapes.model;

import java.util.HashMap;

public class PointCubic extends PointGeneric{

    private double xControl1;
    private double yControl1;
    private double xControl2;
    private double yControl2;
    private double xEnd;
    private double yEnd;

    public PointCubic(double x, double y, double xControl1, double yControl1, double xControl2, double yControl2, double xEnd, double yEnd) {
        super(x, y);
        this.xControl1 = xControl1;
        this.yControl1 = yControl1;
        this.xControl2 = xControl2;
        this.yControl2 = yControl2;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public PointCubic(double x, double y) {
        super(x, y);
    }

    public static PointCubic at(double x, double y, double xControl1, double yControl1, double xControl2, double yControl2, double xEnd, double yEnd) {
        return new PointCubic(x, y, xControl1, yControl1, xControl2, yControl2, xEnd, yEnd);
    }

    @Override
    public HashMap<Integer, Double> getDistance(PointGeneric p) {
        HashMap<Integer, Double> distances = new HashMap<>();
        distances.put(0, Math.sqrt(Math.pow(p.getX() - this.xControl1, 2) + Math.pow(p.getY() - this.yControl1, 2)));
        distances.put(1, Math.sqrt(Math.pow(p.getX() - this.xControl2, 2) + Math.pow(p.getY() - this.yControl2, 2)));
        distances.put(2, Math.sqrt(Math.pow(p.getX() - this.xEnd, 2) + Math.pow(p.getY() - this.yEnd, 2)));
        return distances;
    }

    public double getXControl1() {
        return xControl1;
    }

    public double getYControl1() {
        return yControl1;
    }

    public double getXControl2() {
        return xControl2;
    }

    public double getYControl2() {
        return yControl2;
    }

    public double getXEnd() {
        return xEnd;
    }

    public double getYEnd() {
        return yEnd;
    }

    public void setXControl1(double xControl1) {
        this.xControl1 = xControl1;
    }

    public void setYControl1(double yControl1) {
        this.yControl1 = yControl1;
    }

    public void setXControl2(double xControl2) {
        this.xControl2 = xControl2;
    }

    public void setYControl2(double yControl2) {
        this.yControl2 = yControl2;
    }

    public void setXEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public void setYEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    @Override
    public String toString() {
        return "PointCubic [xControl1=" + xControl1 + ", yControl1=" + yControl1 + ", xControl2=" + xControl2 + ", yControl2=" + yControl2 + ", xEnd=" + xEnd + ", yEnd=" + yEnd + "]";
    }
    
}
