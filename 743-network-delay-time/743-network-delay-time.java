class Solution {
    class Node implements Comparable<Node> {
        int node;
        int weight;
        
        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        
        public int compareTo(Node x) {
            return this.weight - x.weight;
        }
    }
    
    private List<List<Node>> getAdjacencyList(int[][] times, int n) {
        List<List<Node>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());            
        }
        
        for (int i = 0; i < times.length; i++) {
            int fromVertex = times[i][0];
            int toVertex = times[i][1];
            int weight = times[i][2];
            adj.get(fromVertex - 1).add(new Node(toVertex - 1, weight));
        }
        
        return adj;
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Node>> adj = getAdjacencyList(times, n);

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int[] weight = new int[n];
        Arrays.fill(weight, Integer.MAX_VALUE);
        
        weight[k - 1] = 0;
        minHeap.add(new Node(k - 1, 0));
        
        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            
            for(Node child : adj.get(curr.node)) {
                if (weight[child.node] > curr.weight + child.weight) {
                    weight[child.node] = curr.weight + child.weight;
                    minHeap.add(new Node(child.node, weight[child.node]));
                }
            }
        }
        
        int maxWeight = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            maxWeight = Math.max(maxWeight, weight[i]);
        }
        
        return maxWeight == Integer.MAX_VALUE ? -1 : maxWeight;
    }
}