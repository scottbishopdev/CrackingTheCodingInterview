package com.CtCI.Chapter4;

// Problem: Given a directed graph and two nodes (S and E), design an algorithm to find out whether there is a route
// between from S to E.

import com.CtCI.CustomLibrary.DirectedGraphNode;

import java.util.*;

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
        nodeE.children.add(nodeH);
        DirectedGraphNode nodeL = new DirectedGraphNode("L");
        DirectedGraphNode nodeI = new DirectedGraphNode("I", new DirectedGraphNode[]{nodeH, nodeL});
        DirectedGraphNode nodeJ = new DirectedGraphNode("J", new DirectedGraphNode[]{nodeI});
        nodeH.children.add(nodeJ);
        DirectedGraphNode nodeK = new DirectedGraphNode("K", new DirectedGraphNode[]{nodeH});

        ArrayList<DirectedGraphNode> graph = new ArrayList<DirectedGraphNode>( Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL) );

        DepthFirstSearcher DFSer = new DepthFirstSearcher(false);
        BreadthFirstSearcher BFSer = new BreadthFirstSearcher(false);

        System.out.println("===== Doing DFS Searches =====");
        CheckForRouteBetweenNodes(DFSer, graph, nodeG, nodeC);
        CheckForRouteBetweenNodes(DFSer, graph, nodeF, nodeJ);
        CheckForRouteBetweenNodes(DFSer, graph, nodeJ, nodeF);
        CheckForRouteBetweenNodes(DFSer, graph, nodeE, nodeC);
        CheckForRouteBetweenNodes(DFSer, graph, nodeG, nodeB);
        CheckForRouteBetweenNodes(DFSer, graph, nodeK, nodeJ);
        CheckForRouteBetweenNodes(DFSer, graph, nodeK, nodeI);
        CheckForRouteBetweenNodes(DFSer, graph, nodeK, nodeL);

        System.out.println("===== Doing BFS Searches =====");
        CheckForRouteBetweenNodes(BFSer, graph, nodeG, nodeC);
        CheckForRouteBetweenNodes(BFSer, graph, nodeF, nodeJ);
        CheckForRouteBetweenNodes(BFSer, graph, nodeJ, nodeF);
        CheckForRouteBetweenNodes(BFSer, graph, nodeE, nodeC);
        CheckForRouteBetweenNodes(BFSer, graph, nodeG, nodeB);
        CheckForRouteBetweenNodes(BFSer, graph, nodeK, nodeJ);
        CheckForRouteBetweenNodes(BFSer, graph, nodeK, nodeI);
        CheckForRouteBetweenNodes(BFSer, graph, nodeK, nodeL);

    }

    public static void CheckForRouteBetweenNodes(GraphSearcher searcher, ArrayList<DirectedGraphNode> graph, DirectedGraphNode start, DirectedGraphNode end) {

        System.out.println(String.format("Start: '%s', End: '%s'", start.data, end.data));

        if(searcher.FindRouteBetweenNodes(graph, start, end)) {
            System.out.println(String.format("[X] Found a path from '%s' to '%s' using %s.", start.data, end.data, searcher.getName()));
        }
        else {
            System.out.println(String.format("[ ] Could not find a path from '%s' to '%s' using DFS.", start.data, end.data, searcher.getName()));
        }
    }

    public interface GraphSearcher
    {
        String getName();
        Boolean FindRouteBetweenNodes(ArrayList<DirectedGraphNode> graph, DirectedGraphNode start, DirectedGraphNode end);
    }

    public static class DepthFirstSearcher implements GraphSearcher
    {
        private Boolean debug;
        public String getName() { return "DFS"; }

        public DepthFirstSearcher(Boolean logDebug) {
            debug = logDebug;
        }

        private void LogDebug(String message) {
            if(debug) System.out.println("    Debug: " + message);
        }

        public Boolean FindRouteBetweenNodes(ArrayList<DirectedGraphNode> graph, DirectedGraphNode start, DirectedGraphNode end) {
            if (start.data == end.data) {
                LogDebug("Start and end were the same, exiting.");
                return true;
            }  else if(!(graph.contains(start) && graph.contains(end)) ) {
                LogDebug("The graph is missing either the start or end node, exiting.");
                return false;
            } else if (start.children.size() == 0) {
                LogDebug("The starting node has no children.");
                return false;
            } else {
                return WeHaveToGoDeeper(start, end, new ArrayList<>() );
            }
        }

        public Boolean WeHaveToGoDeeper(DirectedGraphNode start, DirectedGraphNode end, ArrayList<DirectedGraphNode> visitedNodes) {
            LogDebug(String.format("Searching for: '%s', starting at '%s'", end.data, start.data));
            if (start.children.size() == 0) {
                LogDebug("-----  GOING UP  -----");
                return false;
            }

            visitedNodes.add(start);

            for (DirectedGraphNode child : start.children) {
                LogDebug(String.format("Searching for: '%s', starting at '%s'", end.data, start.data));

                if (!visitedNodes.contains(child)) {
                    if (child.data == end.data) {
                        LogDebug("Found the search target in the children of this node!");
                        return true;
                    } else {
                        LogDebug("-----GOING DEEPER-----");
                        if (WeHaveToGoDeeper(child, end, visitedNodes))
                            return true;
                    }
                } else {
                    LogDebug(String.format("Node '%s' has already been visited, skipping.", child.data));
                }

            }

            return false;
        }
    }

    public static class BreadthFirstSearcher implements GraphSearcher {
        private Boolean debug;
        public String getName() { return "BFS"; }

        public BreadthFirstSearcher(Boolean logDebug) {
            debug = logDebug;
        }

        private void LogDebug(String message) {
            if (debug) System.out.println("    Debug: " + message);
        }

        public Boolean FindRouteBetweenNodes(ArrayList<DirectedGraphNode> graph, DirectedGraphNode start, DirectedGraphNode end) {
            if (start.data == end.data) {
                LogDebug("Start and end were the same, exiting.");
                return true;
            } else if (!(graph.contains(start) && graph.contains(end))) {
                LogDebug("The graph is missing either the start or end node, exiting.");
                return false;
            } else if (start.children.size() == 0) {
                LogDebug("The starting node has no children.");
                return false;
            } else {

                LinkedList<DirectedGraphNode> checkQueue = new LinkedList<>();
                HashSet<DirectedGraphNode> checkedNodes = new HashSet<>();

                checkQueue.add(start);

                while(!checkQueue.isEmpty())
                {
                    DirectedGraphNode currentNode = checkQueue.pop();
                    for(DirectedGraphNode childNode: currentNode.children) {
                        if(childNode.data == end.data) {
                            return true;
                        } else if (!(checkQueue.contains(childNode) || checkedNodes.contains(childNode))) {
                            checkQueue.push(childNode);
                        }
                        checkedNodes.add(childNode);
                    }
                }

                return false;
            }
        }
    }
}
