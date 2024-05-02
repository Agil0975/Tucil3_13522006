package src;

import java.util.ArrayList;

/* 
 *  Class Node
 *  This class is used to store a node in the priority queue
 */
public class Node implements Comparable<Node>{
    /*  
     *  Attributes
     */
    private String word;                // word = n
    private int cost;                   // cost = f(n)
    private ArrayList<String> path;     // path = [start, ..., parrrent of n]

    /*
     *  Methods
     */
    
    /**
     * Constructor
     * 
     * @param word the word of the node
     * @param cost the cost of the node
     */
    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
        path = new ArrayList<String>();
    }

    /**
     * Getter
     * 
     * @return word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Getter
     * 
     * @return cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Getter
     * 
     * @return path
     */
    public ArrayList<String> getPath() {
        return this.path;
    }

    /**
     * Setter
     * 
     * @param cost the cost of the node
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Setter
     * 
     * @param path array of words
     */
    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    /**
     * Setter
     * Add a word to the path
     * 
     * @param word the word to be added
     */
    public void addPath(String word) {
        this.path.add(word);
    }

    /**
     * Compare the cost of this node with the cost of other node
     * 
     * @param other the other node to be compared
     * @return -1, 0, or 1
     * If the cost of this node is less than the cost of other node, return -1
     * If the cost of this node is equal to the cost of other node, return 0
     * If the cost of this node is greater than the cost of other node, return 1
     */
    @Override
    public int compareTo(Node other) {
        return Integer.compare( this.cost, other.cost);
    }
}
