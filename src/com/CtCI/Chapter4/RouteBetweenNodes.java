package com.CtCI.Chapter4;

// Problem: Given a directed graph and two nodes (S and E), design an algorithm to find out whether there is a route
// between from S to E.

import com.CtCI.CustomLibrary.DirectedGraphNode;

public class RouteBetweenNodes {

    public static void main(String[] args) {

        DirectedGraphNode nodeA = new DirectedGraphNode("A");
        DirectedGraphNode nodeB = new DirectedGraphNode("B");
        DirectedGraphNode nodeC = new DirectedGraphNode("C", new DirectedGraphNode[]{nodeA, nodeB});
        DirectedGraphNode nodeD = new DirectedGraphNode("D", new DirectedGraphNode[]{nodeC});
        DirectedGraphNode nodeE = new DirectedGraphNode("E");
        DirectedGraphNode nodeF = new DirectedGraphNode("F", new DirectedGraphNode[]{nodeD, nodeE});
        DirectedGraphNode nodeG = new DirectedGraphNode("G", new DirectedGraphNode[]{nodeF});

        DirectedGraphNode nodeH = new DirectedGraphNode("H");
        DirectedGraphNode nodeI = new DirectedGraphNode("I", new DirectedGraphNode[]{nodeH});
        DirectedGraphNode nodeJ = new DirectedGraphNode("J", new DirectedGraphNode[]{nodeI});
        nodeH.children.add(nodeJ);
        DirectedGraphNode nodeK = new DirectedGraphNode("K", new DirectedGraphNode[]{nodeH});

        DirectedGraphNode[] graph = {nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK};

    }

    public Boolean CheckForRouteBetweenNodes(DirectedGraphNode[] graph, DirectedGraphNode start, DirectedGraphNode end) {

        return false;
    }

    public Boolean DepthFirstSearch(DirectedGraphNode target) {

        return false;
    }

    public Boolean BreadthFirstSearch(DirectedGraphNode target) {
        return false;
    }

}
