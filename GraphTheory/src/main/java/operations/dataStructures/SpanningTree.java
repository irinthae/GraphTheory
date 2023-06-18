package operations.dataStructures;

import formatter.ListFormatter;
import graph.Edge;

import java.util.List;

public class SpanningTree {
    private List<Edge> edgeList;
    private int cost;

    public SpanningTree(List<Edge> edgeList, int cost) {
        this.edgeList = edgeList;
        this.cost = cost;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cost = " + cost + "\nEdges = " + new ListFormatter<>(edgeList);
    }
}
