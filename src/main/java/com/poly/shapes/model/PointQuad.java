package com.poly.shapes.model;

import java.util.HashMap;

public class PointQuad extends PointGeneric {

    private Double xControl;
    private Double yControl;
    private Double xEnd;
    private Double yEnd;

    public PointQuad(Double x, Double y, Double xControl, Double yControl, Double xEnd, Double yEnd) {
        super(x, y);
        this.xControl = xControl;
        this.yControl = yControl;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public PointQuad(Double x, Double y){
        super(x, y);
    }

    public static PointQuad at(Double x, Double y, Double xControl, Double yControl, Double xEnd, Double yEnd) {
        return new PointQuad(x, y, xControl, yControl, xEnd, yEnd);
    }

    @Override
    public HashMap<Integer, Double> getDistance(PointGeneric p) {
        HashMap<Integer, Double> distances = new HashMap<>();
        distances.put(0, Math.sqrt(Math.pow(p.getX() - this.xControl, 2) + Math.pow(p.getY() - this.yControl, 2)));
        distances.put(1, Math.sqrt(Math.pow(p.getX() - this.xEnd, 2) + Math.pow(p.getY() - this.yEnd, 2)));
        return distances;
    }

    public Double getXControl() {
        return xControl;
    }

    public Double getYControl() {
        return yControl;
    }

    public Double getXEnd() {
        return xEnd;
    }

    public Double getYEnd() {
        return yEnd;
    }

    public void setXControl(Double xControl) {
        this.xControl = xControl;
    }

    public void setYControl(Double yControl) {
        this.yControl = yControl;
    }

    public void setXEnd(Double xEnd) {
        this.xEnd = xEnd;
    }

    public void setYEnd(Double yEnd) {
        this.yEnd = yEnd;
    }

    @Override
    public String toString() {
        return "PointCurve [xControl=" + xControl + ", yControl=" + yControl + ", xEnd=" + xEnd + ", yEnd=" + yEnd + "]";
    }
}
