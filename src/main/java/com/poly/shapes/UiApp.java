package com.poly.shapes;

import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.poly.shapes.model.Point;
import com.poly.shapes.model.PointCubic;
import com.poly.shapes.model.PointQuad;
import com.poly.shapes.model.PointGeneric;
import com.poly.shapes.model.Poly;
import com.poly.shapes.ui.DrawPanel;
import com.poly.shapes.util.StringCurve;
import com.poly.shapes.util.SvgParser;

public class UiApp extends JFrame {
    
    DrawPanel panel;
    Random rnd = new Random();

    public UiApp() {
        setTitle("Polygon");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Poly polygon = createPolygon();
        panel = new DrawPanel(polygon);
        add(panel);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Poly createPolygon(){
        Poly shape = new Poly();
        
        svgShape(shape);
        return shape;
    }

    public void svgShape(Poly shape){
        SvgParser parser = new SvgParser("/blobRel.svg");
        parser.readFile();
        System.out.println(parser.getFileLines().size());
        parser.parseFile();
        List<StringCurve> curves = parser.getCurves();
        shape.loadSvg(curves);
    }

    public void createRandomShape(Poly shape){
        for (int i = 1; i < 10; i++) {
            if (rnd.nextFloat() > 0.25) {
                shape.addPoint(Point.at(rndValue(), 
                                        rndValue()));
            } else {
                PointGeneric previous = shape.getPoints().get(i - 1);
                float chance = rnd.nextFloat();
                if (chance > 0.5) {
                    shape.addPoint(PointQuad.at(previous.getX(), previous.getY(), rndValue(), rndValue(), rndValue(), rndValue()));
                } else {
                    shape.addPoint(PointCubic.at(previous.getX(), previous.getY(), rndValue(), rndValue(), rndValue(), rndValue(), rndValue(), rndValue()));
                }
            }
        }
    }

    public double rndValue(){
        return rnd.nextDouble(100, 400);
    }
    
    public static void main(String[] args) {
        new UiApp();
    }
}
