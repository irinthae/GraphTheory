package operations.dataStructures;

import formatter.ListFormatter;
import graph.Edge;
import graph.Vertex;

import java.util.ArrayList;

public class Component {
    private final ArrayList<Vertex> vertexList;
    private final ArrayList<Edge> edgeList;
    private final int number;

    public Component(int number) {
        this.vertexList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
        this.number = number;
    }

    public ArrayList<Vertex> getVertexList() {
        return vertexList;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    @Override
    public String toString() {
        return "C" + (number + 1) + " = (" +
                new ListFormatter<>(vertexList) + ", " +
                new ListFormatter<>(edgeList) + ")";
    }
}
