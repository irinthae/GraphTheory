package graph;

import formatter.ListFormatter;
import formatter.MatrixFormatter;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertexList = new ArrayList<>();
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private int[][] adjacencyMatrix;
    private int sumVertices;
    private boolean weighted;


    public Graph(int[][] adjacencyMatrix, ArrayList<String> vertexList) {
        setSumVertices(vertexList);
        setAdjacencyMatrix(adjacencyMatrix);
        setVertexList(vertexList);
        setEdgeList();
        setWeighted();
    }

    public Graph(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        this.sumVertices = vertexList.size();
        setWeighted();
        setAdjacencyMatrix();
    }


    public int getSumVertices() {
        return sumVertices;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix.clone();
    }

    public ArrayList<Vertex> getVertexList() {
        return new ArrayList<>(vertexList);
    }

    public ArrayList<Edge> getEdgeList() {
        return new ArrayList<>(edgeList);
    }

    public boolean isWeighted() {
        return weighted;
    }

    public Vertex getVertex(int index) {
        return vertexList.get(index);
    }

    public Edge getEdge(Vertex start, Vertex end) {
        for (Edge edge : edgeList) {
            if (edge.contains(start) && edge.contains(end)) {
                return edge;
            }
        }
        return null;
    }

    private void setSumVertices(ArrayList<String> vertexList) {
        this.sumVertices = vertexList.size();
    }

    private void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    private void setAdjacencyMatrix() {
        this.adjacencyMatrix = new int[sumVertices][sumVertices];
        for (int i = 0; i < sumVertices; i++) {
            for (int j = 0; j < sumVertices; j++) {
                if (checkAdjacent(i, j) != 0) {
                    adjacencyMatrix[i][j] = checkAdjacent(i, j);
                }
            }
        }
    }

    private void setVertexList(ArrayList<String> vertexList) {
        vertexList.forEach(vertex -> this.vertexList.add(new Vertex(vertex, vertexList.indexOf(vertex))));
    }

    private void setEdgeList() {
        for (int i = 0; i < sumVertices; i++) {
            for (int j = i + 1; j < sumVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edgeList.add(new Edge(getVertex(i), getVertex(j), adjacencyMatrix[i][j]));
                }
            }
        }
    }

    private void setWeighted() {
        this.weighted = edgeList.stream()
                                .anyMatch(edge -> edge.getWeight() != 0 && edge.getWeight() != 1);
    }

    private int checkAdjacent(int key1, int key2) {
        Vertex vertex1 = vertexList.get(key1);
        Vertex vertex2 = vertexList.get(key2);

        for (Edge edge : edgeList) {
            if (edge.contains(vertex1) && edge.contains(vertex2) && !vertex1.equals(vertex2)) {
                return edge.getWeight();
            }
        }
        return 0;
    }

    public ArrayList<Vertex> getNeighbourList(Vertex v) {
        ArrayList<Vertex> neighbourList = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[v.getIndex()][i] != 0) {
                neighbourList.add(vertexList.get(i));
            }
        }

        return neighbourList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ADJACENCY MATRIX:\n\n");
        sb.append(new MatrixFormatter(this).getFormattedMatrix());

        sb.append("\n");
        sb.append("VERTICES:\n");
        sb.append(new ListFormatter<>(vertexList));
        sb.append("\n");

        sb.append("\n");
        sb.append("EDGES:\n");
        sb.append(new ListFormatter<>(edgeList));
        sb.append("\n");

        return sb.toString();
    }

}
