package com.poly.shapes.util;

import java.util.ArrayList;
import java.util.List;

public class StringCurve {
    
    private List<NodeCurve> nodeCurves = new ArrayList<NodeCurve>();

    public StringCurve() {
    }

    public List<NodeCurve> getNodeCurves() {
        return nodeCurves;
    }

    public void setNodeCurves(List<NodeCurve> nodeCurves) {
        this.nodeCurves = nodeCurves;
    }

    public void addNodeCurve(NodeCurve nodeCurve) {
        this.nodeCurves.add(nodeCurve);
    }

    @Override
    public String toString() {
        return "StringCurve [nodeCurves=" + nodeCurves + "]";
    }
}
