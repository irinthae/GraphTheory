package operations.algorithms;

import graph.Graph;
import graph.Vertex;

import java.util.*;

public class BFS {

    public static int[] findDistances(Graph graph, Vertex start) {
        Queue<Vertex> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.getSumVertices()];
        int[] distance = new int[graph.getSumVertices()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        queue.offer(start);
        visited[start.getIndex()] = true;
        distance[start.getIndex()] = 0;

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();

            ArrayList<Vertex> neighbours = graph.getNeighbourList(v);

            for (Vertex n : neighbours) {
                if (!visited[n.getIndex()]) {
                    queue.add(n);
                    visited[n.getIndex()] = true;
                    distance[n.getIndex()] = distance[v.getIndex()] + 1;
                }
            }
        }
        return distance;
    }


}
