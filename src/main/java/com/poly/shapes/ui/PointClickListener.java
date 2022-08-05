package com.poly.shapes.ui;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.event.MouseInputAdapter;

import com.poly.shapes.model.Pair;
import com.poly.shapes.model.Point;
import com.poly.shapes.model.PointCubic;
import com.poly.shapes.model.PointGeneric;
import com.poly.shapes.model.PointQuad;
import com.poly.shapes.model.Poly;

public class PointClickListener extends MouseInputAdapter {

    private Poly poly;
    private Pair<Integer, Integer> clickedPoint;
    private DrawPanel drawPanel;

    public PointClickListener(Poly poly, DrawPanel drawPanel) {
        this.poly = poly;
        this.drawPanel = drawPanel;
    }


    //get mouse pressed event
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed at " + e.getX() + "," + e.getY());
        Point p = new Point(e.getX(), e.getY());

        Point2D p2d = new Point2D.Double(e.getX(), e.getY());
        Boolean contained = poly.getGeneralPath().contains(p2d);
        if (contained) {
            System.out.println("Inside Polygon");
        }
        clickedPoint = poly.getCloserPointIndex(p);

        if (clickedPoint != null) {
            int index = clickedPoint.getFirst();
            int type = clickedPoint.getSecond();
            System.out.println("Closer point index is [" + index + "] Value: " + getSubpoint(type, index));
        } else {
            System.out.println("No closer point");
        } 
    }

    public Point getSubpoint(int type, int index) {

        PointGeneric point = poly.getAbsPoints().get(index);
        if (point instanceof Point) {
            return (Point) point;
        } else if (poly.getAbsPoints().get(index) instanceof PointQuad) {
            PointQuad pq = (PointQuad) point;
            if (type == 0){
                return new Point(pq.getXControl(), pq.getYControl());
            }
            if (type == 1){
                return new Point(pq.getXEnd(), pq.getYEnd());
            }
        } else if (poly.getAbsPoints().get(index) instanceof PointCubic) {
            PointCubic pc = (PointCubic) point;
            if (type == 0){
                return new Point(pc.getXControl1(), pc.getYControl1());
            }
            if (type == 1){
                return new Point(pc.getXControl2(), pc.getYControl2());
            }
            if (type == 2){
                return new Point(pc.getXEnd(), pc.getYEnd());
            }
        }
        return null;
    }

    public void setSubpoint(int type, int index, double x, double y){
        PointGeneric point = poly.getAbsPoints().get(index);
        if (point instanceof Point) {
            ((Point) point).setX(x);
            ((Point) point).setY(y);
            poly.getAbsPoints().set(index, point);
        } else if (poly.getAbsPoints().get(index) instanceof PointQuad) {
            PointQuad pq = (PointQuad) point;
            if (type == 0){
                pq.setXControl(x);
                pq.setYControl(y);
                poly.getAbsPoints().set(index, pq);
            }
            if (type == 1){
                pq.setXEnd(x);
                pq.setYEnd(y);
                poly.getAbsPoints().set(index, pq);
            }
        } else if (poly.getAbsPoints().get(index) instanceof PointCubic) {
            PointCubic pc = (PointCubic) point;
            if (type == 0){
                pc.setXControl1(x);
                pc.setYControl1(y);
                poly.getAbsPoints().set(index, pc);
            }
            if (type == 1){
                pc.setXControl2(x);
                pc.setYControl2(y);
                poly.getAbsPoints().set(index, pc);
            }
            if (type == 2){
                pc.setXEnd(x);
                pc.setYEnd(y);
                poly.getAbsPoints().set(index, pc);
            }
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        Point p = new Point(e.getX(), e.getY());
        if (clickedPoint != null) {
            int index = clickedPoint.getFirst();
            int type = clickedPoint.getSecond();
            setSubpoint(type, index, p.getX(), p.getY());
            drawPanel.repaint();
        }
    }
}
