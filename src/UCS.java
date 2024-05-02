package src;

/*
 *  Class UCS
 *  This class is used to implement Uniform Cost Search algorithm
 *  UCS is a search algorithm that finds the shortest path between the initial node and the goal node
 *  It is an uninformed search algorithm that uses a priority queue to expand the node with the lowest cost
 *  f(n) = g(n)
 *  g(n) = Cost of the path from the start node to the current node
 */
public class UCS extends SearchAlgorithm {
    /*
     *  Method
     */
    
    /**
     * Constructor
     * 
     * @param start the start word
     * @param goal the goal word
     * @param dictionary the dictionary of words
     */
    public UCS(String start, String goal, Dictionary dictionary) {
        super(start, goal, dictionary);
    }

    /**
     * Implement UCS algorithm
     */
    public void search() {
        long startTime = System.nanoTime();

        // Create a start node and add it to the priority queue
        Node startNode = new Node(this.start, 0);
        this.lifeNode.add(startNode);
        this.explored.put(startNode.getWord(), true);

        // Loop until the priority queue is empty or the goal node is found
        while (!this.lifeNode.isEmpty()) {
            // Expand the node with the lowest cost
            Node expandNode = this.lifeNode.poll();
            this.explored.put(expandNode.getWord(), true);
            this.VisitedNodes++;
            
            // Check if the goal node is found
            if (expandNode.getWord().equalsIgnoreCase(this.goal)) {
                this.result = expandNode;
                break;
            }

            // Add the neighbors of the expanded node to the priority queue
            for (String neighbor : this.dictionary.getAllOneCharDifferenceStrings(expandNode.getWord())) {
                if (!this.explored.containsKey(neighbor)) { // Check if the neighbor has been explored

                    // g(n) = g(parent) + 1
                    Integer newCost = expandNode.getCost() + 1;
                    
                    // Create a new node and add it to the priority queue
                    Node newNode = new Node(neighbor, newCost);
                    newNode.setPath(new java.util.ArrayList<String>(expandNode.getPath()));
                    newNode.addPath(expandNode.getWord());
                    this.lifeNode.add(newNode);
                }
            }
        }
        long endTime = System.nanoTime();
        this.executionTime = (endTime - startTime) / 1000000;
    }
}