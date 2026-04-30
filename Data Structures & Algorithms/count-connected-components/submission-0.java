class Solution {
    public int countComponents(int n, int[][] edges) {
        // start with each node as its own parent — n separate components
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // every edge that successfully unions two different components reduces our count by 1
        int components = n;
        for (int[] edge : edges) {
            if (union(parent, rank, edge[0], edge[1])) {
                components--;
            }
        }

        return components;
    }

    // find the root of x with path compression so future lookups are faster
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    // attach the smaller tree under the larger one — returns true only if a real merge happened
    private boolean union(int[] parent, int[] rank, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        // already in the same component — no merge, no count change
        if (rootA == rootB) return false;

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
        return true;
    }
}