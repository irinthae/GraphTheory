package operations.dataStructures;

import graph.Vertex;

public class VertexWrapper implements Comparable<VertexWrapper> {
    private final Vertex vertex;
    private int distance;
    private VertexWrapper predecessor;

    public VertexWrapper(Vertex vertex, int distance, VertexWrapper predecessor) {
        this.vertex = vertex;
        this.distance = distance;
        this.predecessor = predecessor;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public VertexWrapper getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(VertexWrapper predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(VertexWrapper o) {
        int compare = Integer.compare(this.distance, o.distance);
        if (compare == 0) {
            compare = this.vertex.compareTo(o.vertex);
        }
        return compare;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
