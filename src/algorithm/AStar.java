package src.algorithm;

import src.dictionary.Dictionary;
import java.util.ArrayList;

/**
 * Class AStar
 * This class is used to implement A* algorithm
 * A* is a search algorithm that finds the shortest path between the initial node and the goal node
 * It is an informed search algorithm that uses a heuristic function to estimate the cost of the cheapest path
 * f(n) = g(n) + h(n)
 * g(n) = Many changes have been made from the start word
 * h(n) = Heuristic function is calculated based on the number of different letters of the target word
 */
public class AStar extends SearchAlgorithm {
    /**
     * Method
     */
    
    /**
     * Constructor
     * 
     * @param start the start word
     * @param goal the goal word
     * @param dictionary the dictionary of words
     */
    public AStar(String start, String goal, Dictionary dictionary) {
        super(start, goal, dictionary);
    }

    /**
     * Abstract method to calculate g(n) and h(n)
     */
    public Integer gn(Node parent) {
    // g(n) = Many changes have been made from the start word
        return parent.getPath().size() + 1;
    }

    public Integer hn(String current) {
    // h(n) = Heuristic function is calculated based on the number of different letters of the target word
        return this.dictionary.wordDistance(current, this.goal);
    }

    /**
     * Implement A* algorithm
     */
    public void search() {
        long startTime = System.nanoTime();

        // Create a start node and add it to the priority queue
        Node startNode = new Node(this.start, this.dictionary.wordDistance(this.start, this.goal));
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
                    
                    // f(n) = g(n) + h(n)
                    Integer fn = gn(expandNode) + hn(neighbor);
                    
                    // Create a new node and add it to the priority queue
                    Node newNode = new Node(neighbor, fn);
                    newNode.setPath(new ArrayList<String>(expandNode.getPath()));
                    newNode.addPath(expandNode.getWord());
                    this.lifeNode.add(newNode);
                }
            }
        }
        long endTime = System.nanoTime();
        this.executionTime = (endTime - startTime) / 1000000;
    }
}