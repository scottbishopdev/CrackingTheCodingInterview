package com.CtCI.Chapter4;

// Problem: Given a directed graph and two nodes (S and E), design an algorithm to find out whether there is a route
// between from S to E.

public class RouteBetweenNodes {

    public static class Graph {
        public Node[] nodes;
    }

    public static class Node {
        public String value;
        public Node[] children;

        public Node(String nodeVal, Node[] nodeChildren) {
            value = nodeVal;
            children = nodeChildren;
        }
    }

    public static void main(String[] args) {

        Node nodeA = new Node("A", new Node[0]);
        Node nodeB = new Node("B", new Node[0]);
        Node nodeC = new Node("C", new Node[]{nodeA, nodeB});
        Node nodeD = new Node("D", new Node[]{nodeC});
        Node nodeE = new Node("E", new Node[0]);
        Node nodeF = new Node("F", new Node[]{nodeD, nodeE});
        Node nodeG = new Node("G", new Node[]{nodeF});

        Node nodeH = new Node("H", new Node[1]);
        Node nodeI = new Node("I", new Node[]{nodeH});
        Node nodeJ = new Node("J", new Node[]{nodeI});
        nodeH.children[0] = nodeJ;
        Node nodeK = new Node("K", new Node[]{nodeH});

        Node[] graph = {nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK};


    }

    public Boolean CheckForRouteBetweenNodes(Node[] graph, Node start, Node end) {



        return false;
    }

    public Boolean DepthFirstSearch(Node target) {
        /*
        for(Node node: target.children) {

        }*/

        return false;
    }

    public Boolean BreadthFirstSearch(Node target) {
        return false;
    }

}
