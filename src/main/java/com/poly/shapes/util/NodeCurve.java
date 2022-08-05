package com.poly.shapes.util;

import java.util.ArrayList;
import java.util.List;

import com.poly.shapes.model.Pair;

public class NodeCurve {
 
    private String nodeType;
    private List<Pair<Double,Double>> nodeAttributes = new ArrayList<Pair<Double,Double>>();

    public NodeCurve(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public List<Pair<Double,Double>> getNodeAttributes() {
        return nodeAttributes;
    }

    public void setNodeAttributes(List<Pair<Double,Double>> nodeAttributes) {
        this.nodeAttributes = nodeAttributes;
    }

    public void addNodeAttribute(Pair<Double,Double> nodeAttribute) {
        this.nodeAttributes.add(nodeAttribute);
    }

    public void addNodeAttribute(Double x, Double y) {
        this.nodeAttributes.add(new Pair<Double,Double>(x,y));
    }

    @Override
    public String toString() {
        return "StringCurve [nodeType=" + nodeType + ", nodeAttributes=" + nodeAttributes + "]";
    }
}
