package com.poly.shapes.ui;

import javax.swing.JPanel;

import com.poly.shapes.model.Point;
import com.poly.shapes.model.PointCubic;
import com.poly.shapes.model.PointQuad;
import com.poly.shapes.model.PointGeneric;
import com.poly.shapes.model.Poly;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

public class DrawPanel extends JPanel{
    
    private Poly polygon;
    private Double originX = 0.0;
    private Double originY = 0.0;

    public DrawPanel(Poly polygon) {
        super();
        this.polygon = polygon;
        PointClickListener listener = new PointClickListener(polygon, this);

        addMouseMotionListener(listener);
        addMouseListener(listener);
    }
    
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.RED);
        g2d.draw(polygon.getGeneralPath());
        
        g.setColor(Color.YELLOW);
        g2d.fill(polygon.getGeneralPath());

        Shape circle;
        Double size = 4.0;
        for (PointGeneric p : polygon.getAbsPoints()) {
            g.setColor(Color.BLACK);
            if (p instanceof Point){
                circle = new Arc2D.Double(p.getX() - 2 + originX, p.getY() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
            }
            if (p instanceof PointQuad){
                g.setColor(Color.RED);
                PointQuad pc = (PointQuad) p;
                circle = new Arc2D.Double(pc.getXEnd() - 2 + originX, pc.getYEnd() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
                g.setColor(Color.ORANGE);
                circle = new Arc2D.Double(pc.getXControl() - 2 + originX, pc.getYControl() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
            }
            if (p instanceof PointCubic){
                g.setColor(Color.RED);
                PointCubic pc = (PointCubic) p;
                circle = new Arc2D.Double(p.getX() - 2 + originX, p.getY() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
                g.setColor(Color.GREEN);
                circle = new Arc2D.Double(pc.getXControl1() - 2 + originX, pc.getYControl1() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
                g.setColor(Color.BLUE);
                circle = new Arc2D.Double(pc.getXControl2() - 2 + originX, pc.getYControl2() - 2 + originY, size, size, 0, 360, Arc2D.CHORD);
                g2d.fill(circle);
            }
                
        }
    }
    
}
