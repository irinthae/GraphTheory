package graph;

import java.util.Objects;

public class Vertex implements Comparable<Vertex>  {
    private int index;
    private String label;

    public Vertex(String label, int index) {
        this.index = index;
        setLabel(label);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        if (label != null) {
            this.label = label;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Vertex that && this.label.equals(that.label);
    }

    //0TODO
    @Override
    public int hashCode() {
        return Objects.hash(label);
    }


    @Override
    public int compareTo(Vertex vertex) {
        if (this.index == vertex.getIndex()) {
            return 0;
        }
        return this.index < vertex.index ? -1 : 1;
    }

    @Override
    public String toString() {
        return label != null ? label : "";
    }

}
