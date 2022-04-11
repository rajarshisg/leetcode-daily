class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean visited[] = new boolean[rooms.size()];
        Queue<Integer> q = new LinkedList<>();
        
        q.add(0);
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            visited[curr] = true;
            for (int child : rooms.get(curr)) {
                if (visited[child]) continue;
                q.add(child);
            }
        }
        
        for(int i = 0; i < visited.length; i++) {
            if (!visited[i]) return false;
        }
        
        return true;
    }
}