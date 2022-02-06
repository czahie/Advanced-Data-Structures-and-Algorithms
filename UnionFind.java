public class UnionFind {
    
    int[] parent;
    int[] rank; // depth

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        // path compression
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int xParent = parent[x];
        int yParent = parent[y];
        if (xParent == yParent) {
            return;
        }

        // union by rank (depth)
        if (rank[xParent] < rank[yParent]) {
            int temp = xParent;
            xParent = yParent;
            yParent = temp;
        }
        parent[yParent] = xParent;
        if (rank[xParent] == rank[yParent]) {
            rank[xParent]++;
        }
    }
}
