class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Boolean> isCyclePresent = new HashMap<>();
        boolean[] visited = new boolean[graph.length];
        boolean[] currVisit = new boolean[graph.length];
        
        for (int i = 0; i < graph.length; i++) {
            
            if (!cycleCheck(i, graph, visited, isCyclePresent, currVisit)) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    private boolean cycleCheck(int vertex, int[][] graph, boolean[] visited, HashMap<Integer, Boolean> isCyclePresent, boolean[] currVisit) {
        if (isCyclePresent.containsKey(vertex)) {
            return true;
        }
        
        visited[vertex] = true;
        currVisit[vertex] = true;
        
        for (int child : graph[vertex]) {
            if (!visited[child]) {
                boolean currAns = cycleCheck(child, graph, visited, isCyclePresent, currVisit);
                if (currAns) {
                    isCyclePresent.put(vertex, true);
                    return true;
                }
            } else if (visited[child] && currVisit[child] == true) {
                isCyclePresent.put(vertex, true);
                return true;
            }
            
        }
        
        currVisit[vertex] = false;
        return false;
    }
}