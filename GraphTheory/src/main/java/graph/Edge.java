package graph;

import java.util.Arrays;
import java.util.Objects;


public class Edge implements Comparable<Edge> {
    private final Vertex[] vertices = new Vertex[2];
    private int weight;

    public Edge(Vertex vertex1, Vertex vertex2, int weight) {
        setVertices(vertex1, vertex2);
        setWeight(weight);
    }

    public Vertex getVertex1() {
        return vertices[0];
    }

    public Vertex getVertex2() {
        return vertices[1];
    }

    public int getWeight() {
        return weight;
    }

    private void setVertices(Vertex vertex1, Vertex vertex2) {
        this.vertices[0] = vertex1;
        this.vertices[1] = vertex2;
    }

    private void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean contains(Vertex vertex) {
        return  vertex.equals(vertices[0]) || vertex.equals(vertices[1]);
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.weight == edge.weight ) {
            return 0;
        }
        return this.weight < edge.weight ? -1 : 1;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(weight);
        result = 31 * result + Arrays.hashCode(vertices);
        return result;
    }

    @Override
    public boolean equals(Object edge) {
        return edge instanceof Edge that && (that.contains(this.getVertex1()) && that.contains(this.getVertex2()));
    }

    //Ohne Gewichtung
    @Override
    public String toString() {
        return "[" + vertices[0] + "," + vertices[1] + "]";
    }


}
