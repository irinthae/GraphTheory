package operations.dataStructures;

import graph.Graph;
import graph.Vertex;

public class UnionFind {
    private int size;
    private int[] sz;
    private Vertex[] id;
    private int numComponents;

    public UnionFind(Graph graph) {
        this.size = graph.getSumVertices();
        this.numComponents = size;
        this.sz = new int[size];
        this.id = new Vertex[size];

        for (int i = 0; i < size; i++) {
            id[i] = graph.getVertex(i);
            sz[i] = 1;
        }
    }

    public int getNumComponents() {
        return numComponents;
    }

    public Vertex find(Vertex v) {
        Vertex root = v;

        while (root != id[root.getIndex()]) {
            root = id[root.getIndex()];
        }

        compressPath(v, root);

        return root;
    }

    private void compressPath(Vertex v, Vertex root) {
        while (v != root) {
            Vertex next = id[v.getIndex()];
            id[v.getIndex()] = root;
            v = next;
        }
    }

    public boolean isConnected(Vertex v, Vertex u) {
        return find(v).equals(find(u));
    }

    private int componentSize(Vertex v) {
        return sz[find(v).getIndex()];
    }

    public int getSize() {
        return size;
    }

    public void unify(Vertex v, Vertex u) {
        Vertex root1 = find(v);
        Vertex root2 = find(u);

        if (root1.equals(root2)) {
            return;
        }

        if (sz[root1.getIndex()] < sz[root2.getIndex()]) {
            sz[root2.getIndex()] += sz[root1.getIndex()];
            id[root1.getIndex()] = root2;
        } else {
            sz[root1.getIndex()] += sz[root2.getIndex()];
            id[root2.getIndex()] = root1;
        }

        numComponents--;
    }
}
