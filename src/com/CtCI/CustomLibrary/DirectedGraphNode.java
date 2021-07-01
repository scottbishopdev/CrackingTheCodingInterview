package com.CtCI.CustomLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedGraphNode {
    public String data;
    public ArrayList<DirectedGraphNode> children;

    public DirectedGraphNode(String value) {
        data = value;
        children = new ArrayList<DirectedGraphNode>();
    }

    public DirectedGraphNode(String value, DirectedGraphNode[] nodeChildren) {
        this(value, new ArrayList<DirectedGraphNode>(Arrays.asList(nodeChildren)));
    }

    public DirectedGraphNode(String value, ArrayList<DirectedGraphNode> nodeChildren) {
        data = value;
        children = nodeChildren;
    }
}
