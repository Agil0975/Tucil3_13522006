package src.algorithm;

import src.dictionary.Dictionary;
import java.util.*;

/**
 * Abstract Class SearchAlgorithm
 * This class is used to implement search algorithms
 */
public abstract class SearchAlgorithm {
    /**
     * Attributes
     */
    protected String start;                         // Start word
    protected String goal;                          // Goal word
    protected Dictionary dictionary;                // Dictionary of words
    protected String[] wordWithSameLengthWithQuery; // Array of words with the same length as the query
    protected Integer VisitedNodes = 0;             // Number of visited nodes
    protected Long executionTime;                   // Execution time
    protected Node result;                          // Result of the search
    protected PriorityQueue<Node> lifeNode;         // Priority queue of nodes
    protected Map<String, Boolean> explored;        // Map of explored nodes, key = word, value = true (if the word has been expanded)

    /**
     * Methods
     */
    
    /**
     * Constructor
     * 
     * @param start the start word
     * @param goal the goal word
     * @param dictionary the dictionary of words
     */
    public SearchAlgorithm(String start, String goal, Dictionary dictionary) {
        this.start = start;
        this.goal = goal;
        this.dictionary = dictionary;
        this.lifeNode = new PriorityQueue<Node>();
        this.explored = new HashMap<String, Boolean>();
        this.result = null;
        this.wordWithSameLengthWithQuery = dictionary.getAllWordsWithNLength(start.length());
    }

    /**
     * Getter
     * 
     * @return the result of the search
     */
    public Node getResult() {
        return this.result;
    }

    /**
     * Getter
     * 
     * @return the number of visited nodes
     */
    public Integer getVisitedNodes() {
        return this.VisitedNodes;
    }

    /**
     * Getter
     * 
     * @return the execution time
     */
    public Long getExecutionTime() {
        return this.executionTime;
    }

    /**
     * Abstract method to implement search algorithm
     */
    public abstract void search();

    /**
     * Abstract method to calculate g(n) and h(n)
     */
    protected abstract Integer gn(Node parent);
    protected abstract Integer hn(String current);
}