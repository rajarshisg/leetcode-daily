class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n];
        Arrays.fill(color, -1);
        
        List<List<Integer>> adj = getAdjacencyList(dislikes, n);
        
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                boolean res = isBipartite(i, adj, color);
                if (!res) return false;
            }
        }
        
        return true;
    }
    
    private boolean isBipartite(int v, List<List<Integer>> adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        color[v] = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            int currColor = color[curr];
            
            for (int child : adj.get(curr)) {
                if (color[child] == -1) {
                    color[child] = 1 - currColor;
                    q.add(child);
                } else if (color[child] == color[curr]) {
                    return false;
                }
            }
        }
        
        return true;
    } 
    
    private List<List<Integer>> getAdjacencyList(int[][] dislikes, int n) {
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < dislikes.length; i++) {
            adj.get(dislikes[i][0] - 1).add(dislikes[i][1] - 1);
            adj.get(dislikes[i][1] - 1).add(dislikes[i][0] - 1);
        }
        
        return adj;
    }
}