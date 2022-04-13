class Solution {
    // https://www.youtube.com/watch?v=aY5eXeJICss
    public int regionsBySlashes(String[] grid) {
        if (grid.length == 0) return 0;
        
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
        int numRegions = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length(); col++) {
                for (int region = 0; region < 4; region++) {
                    if (!visited[row][col][region]) {
                        dfs(row, col, region, grid, visited);
                        numRegions++;
                    }
                }
            }
        }
        
        return numRegions;
    }
    
    private void dfs(int row, int col, int region, String[] grid, boolean[][][] visited) {
        if (!isValidIndex(row, col, grid.length, grid[0].length()) || visited[row][col][region]) return;
        
        visited[row][col][region] = true;
        if (region == 0) {
                dfs(row - 1, col, 2, grid, visited);
            }
          else if (region == 1) {
                dfs(row, col + 1, 3, grid, visited);
            }
          else if (region == 2) {
                dfs(row + 1, col, 0, grid, visited);
            }
           else if (region == 3) {
                dfs(row, col - 1, 1, grid, visited);
            }
        
        if (grid[row].charAt(col) != '\\') {
            dfs(row, col, region ^ 3, grid, visited);
        }
        
        if (grid[row].charAt(col) != '/') {
            dfs(row, col, region ^ 1, grid, visited);
        }

    }
    
    private boolean isValidIndex(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}