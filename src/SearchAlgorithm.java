package src;

import java.util.*;

/*
 *  Abstract Class SearchAlgorithm
 *  This class is used to implement search algorithms
 */
public abstract class SearchAlgorithm {
    /*
     *  Attributes
     */
    protected String start;                     // Start word
    protected String goal;                      // Goal word
    protected Dictionary dictionary;            // Dictionary of words
    protected Integer VisitedNodes = 0;         // Number of visited nodes
    protected Long executionTime;               // Execution time
    protected Node result;                      // Result of the search
    protected PriorityQueue<Node> lifeNode;     // Priority queue of nodes
    protected Map<String, Boolean> explored;    // Map of explored nodes, key = word, value = true (if the word has been expanded)

    /*
     *  Methods
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
    }

    /**
     * Print the result of the search
     * print length of path, path, number of visited nodes, and execution time (in s and ms)
     */
    public void printResult() {
    System.out.println();
    System.out.println("=============== Hasil Pencarian ===============");
        if (this.result == null) {
            System.out.println("Tidak ada solusi ditemukan.");
        } else {
            Integer panjangPath = this.result.getPath().size();
            System.out.println("Panjang jalur: " + (panjangPath));

            System.out.println("Jalur:");
            for (int i = 0; i < panjangPath; i++) {
                System.out.println("   "+ (i + 1) + ". " + this.result.getPath().get(i).toLowerCase());
            }
            System.out.println("   " + (panjangPath + 1) + ". " + this.result.getWord().toLowerCase());
            
            System.out.println("Banyak node dikunjungi  : " + this.VisitedNodes + " node");
            printExecutionTime();
        }
    }

    /**
     * Print the execution time
     */
    private void printExecutionTime() {
        if (this.executionTime > 1000) {
            System.out.println("Waktu eksekusi          : " + this.executionTime / 1000 + " s " + this.executionTime % 1000 + " ms");
        } else {
            System.out.println("Waktu eksekusi          : " + this.executionTime + " ms");
        }
    }

    /**
     * Abstract method to implement search algorithm
     */
    public abstract void search();

    /**
     * Abstract method to calculate g(n) and h(n)
     */
    public abstract Integer gn(Node parent);
    public abstract Integer hn(String current);
}