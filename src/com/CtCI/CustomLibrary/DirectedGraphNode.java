package com.CtCI.CustomLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedGraphNode {
    public String data;
    public ArrayList<DirectedGraphNode> children;

    public DirectedGraphNode(String d) {
        data = d;
        children = new ArrayList<DirectedGraphNode>();
    }

    public DirectedGraphNode(String d, DirectedGraphNode[] nodeChildren) {
        this(d, new ArrayList<DirectedGraphNode>(Arrays.asList(nodeChildren)));
    }

    public DirectedGraphNode(String d, ArrayList<DirectedGraphNode> nodeChildren) {
        data = d;
        children = nodeChildren;
    }
}