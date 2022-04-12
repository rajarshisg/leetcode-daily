class Solution {
    public int removeStones(int[][] stones) {
        Set<int[]> visited = new HashSet<>();
        int count = 0;
        
        for (int[] curr : stones) {
            if (!visited.contains(curr)) {
                dfs(curr, stones, visited);
                count++;
            }
        }
        
        return stones.length - count;
    }
    
    private void dfs(int[] v, int[][] stones, Set<int[]> visited) {
        visited.add(v);
        
        for (int[] child : stones) {
            if (!visited.contains(child)) {
                if (child[0] == v[0] || child[1] == v[1]) {
                    dfs(child, stones, visited);
                }
            }
        }
    }
}