package operations;

import graph.Graph;
import graph.Vertex;
import operations.algorithms.BFS;
import operations.algorithms.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphProperties {

    public static int[][] getDistanceMatrix(Graph graph) {
        int[][] distanceMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceMatrix[i] = getDistances(graph, graph.getVertex(i));
        }

        return distanceMatrix;
    }

    public static int getEccentricity(Graph graph, Vertex v) {
        int[] distances = getDistances(graph, v);
        return Arrays.stream(distances).max().getAsInt();
    }

    private static int[] getDistances(Graph graph, Vertex v){
        return graph.isWeighted() ?
                Dijkstra.findDistances(graph, v) :
                BFS.findDistances(graph, v);
    }

    private static List<Integer> getEccentricities(Graph graph) {
        ArrayList<Vertex> vertexList  = graph.getVertexList();
        ArrayList<Integer> eccentricityList = new ArrayList<>();

        vertexList.forEach(vertex -> eccentricityList.add(getEccentricity(graph, vertex)));

        return eccentricityList;
    }

    public static int getRadius(Graph graph) {
        return Collections.min(getEccentricities(graph));
    }

    public static int getDiameter(Graph graph) {
        return Collections.max(getEccentricities(graph));
    }

    public static List<Vertex> getCenter(Graph graph) {
        int radius = getRadius(graph);
        List<Integer> eccentricities = getEccentricities(graph);

        return graph.getVertexList()
                        .stream()
                        .filter(vertex -> getEccentricities(graph).get(vertex.getIndex()) == radius)
                        .toList();
    }
}
