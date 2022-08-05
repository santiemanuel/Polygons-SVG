package com.poly.shapes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SvgParser {
    
    private String fileName = "";
    private List<String> fileLines = new ArrayList<String>();
    private List<StringCurve> curves = new ArrayList<StringCurve>();

    public SvgParser(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileLines(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public void readFile(){

        var lines = getFileLines();
        String line = "";

        InputStream is = SvgParser.class.getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        try {
            while ((line = br.readLine()) != null) {
                if (line.startsWith("       d")) {
                    line = line.replace("       d=", "");
                    line = line.replace("\"", "");
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseFile() {
        var lines = getFileLines();
        for (String line: lines) {
            var elements = line.split(" ");
            StringCurve curve = new StringCurve();
            NodeCurve node = new NodeCurve(elements[0]);
            var values = elements[1].split(",");
            node.addNodeAttribute(Double.valueOf(values[0]), Double.valueOf(values[1]));
            if (!elements[2].contains("c")){
                int index = node.getNodeAttributes().size();
                
                node.getNodeAttributes().remove(index - 1);
                for (int i = 2; i < elements.length; i++) {
                    values = elements[i].split(",");
                    node.addNodeAttribute(Double.valueOf(values[0]), Double.valueOf(values[1]));
                }
                curve.addNodeCurve(node);
            } else{
                NodeCurve bezierCurve = new NodeCurve(elements[2]);
                bezierCurve.addNodeAttribute(node.getNodeAttributes().get(0).getFirst(), node.getNodeAttributes().get(0).getSecond());
                for (int i = 3; i < elements.length; i++) {
                    values = elements[i].split(",");
                    bezierCurve.addNodeAttribute(Double.valueOf(values[0]), Double.valueOf(values[1]));
                }
                curve.addNodeCurve(bezierCurve);
            }
            curves.add(curve);            
        }
    }

    public List<StringCurve> getCurves() {
        return curves;
    }
}
