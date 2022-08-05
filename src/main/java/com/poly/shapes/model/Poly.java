package com.poly.shapes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.poly.shapes.util.NodeCurve;
import com.poly.shapes.util.StringCurve;

import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class Poly {
    
    private List<PointGeneric> points;
    private List<PointGeneric> absPoints;
    private PointGeneric origin;

    public Poly(Point origin) {
        this.points = new ArrayList<PointGeneric>();
        this.absPoints = new ArrayList<PointGeneric>();
        this.origin = origin;
    }

    public Poly(){
        this.points = new ArrayList<PointGeneric>();
        this.absPoints = new ArrayList<PointGeneric>();
    }

    public Poly(List<PointGeneric> points) {
        this.points = points;
    }

    public void genAbsolutePoints(){
        GeneralPath path = new GeneralPath();
        path.moveTo(origin.getX(), origin.getY());
        getAbsPoints().add(new Point(origin.getX(), origin.getY()));
        Point2D initPoint = path.getCurrentPoint();
        System.out.println("initPoint: " + initPoint);
        //remove the first point from the list
        getPoints().remove(0);
        for (PointGeneric p : getPoints()) {
            if (p instanceof Point){
                Point pnt = (Point) p;
                Double x = pnt.getX() + initPoint.getX();
                Double y = pnt.getY() + initPoint.getY();

                getAbsPoints().add(new Point(x, y));
                path.lineTo(x, y);
            }
                
            if (p instanceof PointQuad){
                PointQuad pc = (PointQuad) p;
                path.quadTo(initPoint.getX() + pc.getXControl(), initPoint.getY() + pc.getYControl(),
                            initPoint.getX() + pc.getXEnd(), initPoint.getY() + pc.getYEnd());
                
            }
            if (p instanceof PointCubic){
                PointCubic pc = (PointCubic) p;
                Double controlX1 = initPoint.getX() + pc.getXControl1();
                Double controlY1 = initPoint.getY() + pc.getYControl1();
                Double controlX2 = initPoint.getX() + pc.getXControl2();
                Double controlY2 = initPoint.getY() + pc.getYControl2();
                Double endX = initPoint.getX() + pc.getXEnd();
                Double endY = initPoint.getY() + pc.getYEnd();

                getAbsPoints().add(new PointCubic(initPoint.getX(), initPoint.getY(), controlX1, controlY1, controlX2, controlY2, endX, endY));
                path.curveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
            }
            initPoint = path.getCurrentPoint();
        }
    }

    public GeneralPath getGeneralPath(){
        GeneralPath path = new GeneralPath();
        path.moveTo(origin.getX(), origin.getY());
        for (PointGeneric p : getAbsPoints()) {
            if (p instanceof Point){
                path.lineTo(p.getX(), p.getY());
            }
            if (p instanceof PointQuad){
                PointQuad pc = (PointQuad) p;
                path.quadTo(pc.getXControl(), pc.getYControl(), pc.getXEnd(), pc.getYEnd());     
            }
            if (p instanceof PointCubic){
                PointCubic pc = (PointCubic) p;
                path.curveTo(pc.getXControl1(), pc.getYControl1(), pc.getXControl2(), pc.getYControl2(), pc.getXEnd(), pc.getYEnd());
            }
        }
        path.closePath();
        return path;
    }

    public void loadSvg(List<StringCurve> curves){
        for (StringCurve curve : curves) {
            for (NodeCurve node : curve.getNodeCurves()) {
                if (node.getNodeType().equals("m")){
                    for (Pair<Double, Double> pair : node.getNodeAttributes()) {
                        this.points.add(new Point(pair.getFirst(), pair.getSecond()));
                    }
                }
                if (node.getNodeType().equals("q")){
                    Pair<Double, Double> start = node.getNodeAttributes().get(0);
                    Pair<Double, Double> control = node.getNodeAttributes().get(1);
                    Pair<Double, Double> end = node.getNodeAttributes().get(2);
                    this.points.add(new PointQuad(start.getFirst(), start.getSecond(),
                                                   control.getFirst(), control.getSecond(),
                                                   end.getFirst(), end.getSecond()));
                }
                if (node.getNodeType().equals("c")){
                    int size = node.getNodeAttributes().size();
                    Pair<Double, Double> start = node.getNodeAttributes().get(0);
                    for (int i = 0; (i * 3) < size; i += 3) {
                        Pair<Double, Double> control1 = node.getNodeAttributes().get(i + 1);
                        Pair<Double, Double> control2 = node.getNodeAttributes().get(i + 2);
                        Pair<Double, Double> end = node.getNodeAttributes().get(i + 3);
                        this.points.add(new PointCubic(start.getFirst(), start.getSecond(),
                                                       control1.getFirst(), control1.getSecond(),
                                                       control2.getFirst(), control2.getSecond(),
                                                       end.getFirst(), end.getSecond()));
                    }
                    
                }
            }
        }
        this.origin = getPoints().get(0);
        genAbsolutePoints();  
    }

    public Pair<Integer, Integer> getCloserPointIndex(PointGeneric p){
        int index = -1;
        int subIndex = -1;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < absPoints.size(); i++) {
            HashMap<Integer, Double> distances = absPoints.get(i).getDistance(p);
            for (int j = 0; j < distances.size(); j++) {
                if (distances.get(j) < minDistance) {
                    minDistance = distances.get(j);
                    index = i;
                    subIndex = j;
                }
            }
        }
        if (minDistance <= 4){
            return new Pair<Integer, Integer>(index, subIndex);
        }
        return null;
    }

    public Poly addPoint(PointGeneric point){
        this.points.add(point);
        return this;
    }

    public List<PointGeneric> getPoints() {
        return points;
    }

    public void setPoints(List<PointGeneric> points) {
        this.points = points;
    }

    //get bounding box
    public Rectangle getBoundingBox() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (PointGeneric point : points) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
            if (point.getY() < minY) {
                minY = point.getY();
            }
            if (point.getX() > maxX) {
                maxX = point.getX();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        return new Rectangle((int)minX, (int)minY, (int)(maxX - minX), (int)(maxY - minY));
    }

    public PointGeneric getOrigin() {
        return origin;
    }

    public void setOrigin(PointGeneric origin) {
        this.origin = origin;
    }

    public Rectangle getBoundingBoxGeneralPath() {
        return getGeneralPath().getBounds();
    }

    public List<PointGeneric> getAbsPoints() {
        return absPoints;
    }

    public void setAbsPoints(List<PointGeneric> absPoints) {
        this.absPoints = absPoints;
    }

    @Override
    public String toString() {
        return "Polygon [points=" + points + "]";
    }

}
