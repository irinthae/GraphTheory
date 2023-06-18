package operations.algorithms;

import graph.Graph;
import graph.Vertex;
import operations.dataStructures.VertexWrapper;

import java.util.*;

public class Dijkstra {

    public static int[] findDistances(Graph graph, Vertex start) {
        Map<Vertex, VertexWrapper> vertexWrappers = new HashMap<>();
        TreeSet<VertexWrapper> queue = new TreeSet<>();
        int[] distances = new int[graph.getSumVertices()];
        boolean[] finished = new boolean[graph.getSumVertices()];
        Arrays.fill(distances, Integer.MAX_VALUE);

        queue.add(discoverStartVertex(start, vertexWrappers));

        while (!queue.isEmpty()) {
            VertexWrapper vertexWrapper = queue.pollFirst();
            Vertex vertex = vertexWrapper.getVertex();
            distances[vertex.getIndex()] = vertexWrapper.getDistance();
            finished[vertex.getIndex()] = true;

            List<Vertex> neighbours = graph.getNeighbourList(vertex);

            for (Vertex neighbour : neighbours) {
                if(finished[neighbour.getIndex()]) {
                    continue;
                }
                int distance = graph.getEdge(vertex, neighbour).getWeight();
                int totalDistance = vertexWrapper.getDistance() + distance;

                VertexWrapper neighbourWrapper = vertexWrappers.get(neighbour);

                if (neighbourWrapper == null) {
                    neighbourWrapper = new VertexWrapper(neighbour, totalDistance, vertexWrapper);
                    vertexWrappers.put(neighbour, neighbourWrapper);
                    queue.add(neighbourWrapper);
                } else if (totalDistance < neighbourWrapper.getDistance()) {
                    queue.remove(neighbourWrapper);
                    neighbourWrapper.setDistance(totalDistance);
                    neighbourWrapper.setPredecessor(vertexWrapper);
                    queue.add(neighbourWrapper);
                }
            }
        }

        return distances;
    }

    private static VertexWrapper discoverStartVertex(Vertex start, Map<Vertex, VertexWrapper> vertexWrappers) {
        VertexWrapper startWrapper = new VertexWrapper(start, 0, null);
        vertexWrappers.put(start, startWrapper);

        return startWrapper;
    }

}
