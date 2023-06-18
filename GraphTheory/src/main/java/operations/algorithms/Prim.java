package operations.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import operations.dataStructures.SpanningTree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    public static SpanningTree findMST(Graph graph, ArrayList<Vertex> vertexList, ArrayList<Edge> originalEdgeList) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        ArrayList<Edge> edgeList = createEdgeList(originalEdgeList);
        List<Edge> mstEdgeList = new ArrayList<>();
        boolean[] visited = new boolean[graph.getSumVertices()];
        int numberOfEdgesInMST = vertexList.size() - 1;
        int cost = 0;

        Vertex vertex = vertexList.get(0);
        visited[vertex.getIndex()] = true;
        addEdgesToPriorityQueue(vertex, edgeList, visited, priorityQueue);

        while (!priorityQueue.isEmpty() && mstEdgeList.size() != numberOfEdgesInMST) {
            Edge edge = priorityQueue.poll();
            vertex = edge.getVertex2();

            if (visited[vertex.getIndex()]) continue;

            mstEdgeList.add(edge);
            cost += edge.getWeight();

            visited[vertex.getIndex()] = true;
            addEdgesToPriorityQueue(vertex, edgeList, visited, priorityQueue);
        }

        return mstEdgeList.size() == numberOfEdgesInMST ? new SpanningTree(mstEdgeList, cost) : null;
    }

    private static ArrayList<Edge> createEdgeList(ArrayList<Edge> originalEdgeList) {
        ArrayList<Edge> edgeList = new ArrayList<>(originalEdgeList);

        for (Edge edge  : originalEdgeList) {
            edgeList.add(new Edge(edge.getVertex2(), edge.getVertex1(), edge.getWeight()));
        }

        return edgeList;
    }

    private static void addEdgesToPriorityQueue(Vertex vertex, List<Edge> originalEdgeList, boolean[] visited, PriorityQueue<Edge> priorityQueue) {
        List<Edge> incidentEdges = originalEdgeList.stream()
                                                    .filter(edge -> edge.getVertex1() == vertex)
                                                    .toList();

        for (Edge edge : incidentEdges) {
            if (!visited[edge.getVertex2().getIndex()]) {
                priorityQueue.add(edge);
            }
        }
    }
}
