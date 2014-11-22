

public class SAP {
    private Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // O(E + V)
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int minAncestorLen = Integer.MAX_VALUE;
        int minAncestor = -1;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                if (bfsv.distTo(i) + bfsw.distTo(i) < minAncestorLen) {
                    minAncestorLen = bfsv.distTo(i) + bfsw.distTo(i);
                    minAncestor = i;
                }
            }
        }

        if (minAncestorLen == Integer.MAX_VALUE)
            return -1;
        return minAncestorLen;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // O(E + V)
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int minAncestorLen = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                if (bfsv.distTo(i) + bfsw.distTo(i) < minAncestorLen) {
                    minAncestorLen = bfsv.distTo(i) + bfsw.distTo(i);
                    minAncestor = i;
                }
            }
        }

        return minAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    // O(E + V)
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int minAncestorLen = Integer.MAX_VALUE;
        int minAncestor = -1;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                if (bfsv.distTo(i) + bfsw.distTo(i) < minAncestorLen) {
                    minAncestorLen = bfsv.distTo(i) + bfsw.distTo(i);
                    minAncestor = i;
                }
            }
        }

        if (minAncestorLen == Integer.MAX_VALUE)
            return -1;

        return minAncestorLen;
    }


    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    // O(E + V)
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int minAncestorLen = Integer.MAX_VALUE;
        int minAncestor = -1;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                if (bfsv.distTo(i) + bfsw.distTo(i) < minAncestorLen) {
                    minAncestorLen = bfsv.distTo(i) + bfsw.distTo(i);
                    minAncestor = i;
                }
            }
        }
        return minAncestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
