package com.poly.shapes;

import java.util.List;

import com.poly.shapes.util.StringCurve;
import com.poly.shapes.util.SvgParser;

public class App 
{
    public static void main( String[] args )
    {
        SvgParser parser = new SvgParser("/pikaRel.svg");
        parser.readFile();
        parser.parseFile();

        List<StringCurve> curves;

        curves = parser.getCurves();

        for (StringCurve curve : curves) {
            System.out.println(curve);
        }
    }
}
