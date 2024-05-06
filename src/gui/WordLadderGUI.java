package src.gui;

import src.algorithm.*;
import src.dictionary.Dictionary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class WordLadderGUI
 * This class is used to create the GUI for Word Ladder Solver
 */
public class WordLadderGUI extends JFrame {
    /**
     * Attributes
     */
    private Dictionary dictionary;              // Dictionary of words
    private JTextField startWordField;          // Start word field
    private JTextField goalWordField;           // Goal word field
    private JComboBox<String> algoritmaBox;     // Algorithm choice box
    private JButton searchButton;               // Search button
    private JTextArea outputArea;               // Output
    private JScrollPane scrollPane;             // Scroll pane

    /**
     * Methods
     */

    /**
     * Constructor
     * 
     * @param dictionary the dictionary of words
     */
    public WordLadderGUI(Dictionary dictionary) {
        this.dictionary = dictionary;

        // Create Window
        setTitle("Word Ladder Solver");
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Selamat datang di Word Ladder Solver!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBounds(20, 0, 360, 25);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel);

        // input start word
        JLabel startWordLabel = new JLabel("Kata Awal");
        startWordLabel.setBounds(20, 30, 80, 25);
        panel.add(startWordLabel);

        startWordField = new JTextField(20);
        startWordField.setBounds(100, 30, 265, 25);
        panel.add(startWordField);

        // input goal word
        JLabel goalWordLabel = new JLabel("Kata Akhir");
        goalWordLabel.setBounds(20, 60, 80, 25);
        panel.add(goalWordLabel);

        goalWordField = new JTextField(20);
        goalWordField.setBounds(100, 60, 265, 25);
        panel.add(goalWordField);

        // input algorithm choice
        JLabel algorithmLabel = new JLabel("Algoritma");
        algorithmLabel.setBounds(20, 90, 80, 25);
        panel.add(algorithmLabel);

        String[] algoritmaOptions = {"Uniform Cost Search Algorithm", "Greedy Best First Search Algorithm", "A* Algorithm"};
        algoritmaBox = new JComboBox<>(algoritmaOptions);
        algoritmaBox.setBounds(100, 90, 265, 25);
        panel.add(algoritmaBox);

        // output area
        outputArea = new JTextArea();
        scrollPane = new JScrollPane(outputArea); // scroll pane
        scrollPane.setBounds(20, 150, 345, 495);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        panel.add(scrollPane);

        // search button
        searchButton = new JButton("Cari");
        searchButton.setBounds(160, 120, 75, 25);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runAlgorithm();
            }
        });
        panel.add(searchButton);

        // Add panel to frame
        add(panel);
        setVisible(true);
    }

    /**
     * Method to run the search algorithm
     */
    private void runAlgorithm() {
        String startWord = startWordField.getText().trim();
        String goalWord = goalWordField.getText().trim();
        String algoritma = (String) algoritmaBox.getSelectedItem();

        // input validation
        if (startWord.equals("") || goalWord.equals("")) {
            JOptionPane.showMessageDialog(this, "Kata awal atau kata tujuan tidak boleh kosong.");
            return;
        } else {
            if (startWord.length() != goalWord.length()) {
                JOptionPane.showMessageDialog(this, "Kata awal dan kata tujuan harus memiliki panjang yang sama.");
                return;
            } else {
                if (!dictionary.isWordInDictionary(startWord)) {
                    JOptionPane.showMessageDialog(this, "Kata awal tidak ada di dalam kamus.");
                    return;
                } else {
                    if (!dictionary.isWordInDictionary(goalWord)) {
                        JOptionPane.showMessageDialog(this, "Kata tujuan tidak ada di dalam kamus.");
                        return;
                    }
                }
            }
        }

        // Run the selected algorithm
        SearchAlgorithm searchAlgorithm = null;
        switch (algoritma) {
            case "Uniform Cost Search Algorithm":
                System.out.println("Running UCS...");
                searchAlgorithm = new UCS(startWord, goalWord, dictionary);
                break;
            case "Greedy Best First Search Algorithm":
                System.out.println("Running Greedy BFS...");
                searchAlgorithm = new GreedyBeFS(startWord, goalWord, dictionary);
                break;
            case "A* Algorithm":
                System.out.println("Running A*...");
                searchAlgorithm =  new AStar(startWord, goalWord, dictionary);
                break;
        }

        try {
            searchAlgorithm.search();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished!");
        
        // Output the result
        Node result = searchAlgorithm.getResult();
        outputArea.setText(printResult(result, searchAlgorithm.getExecutionTime(), searchAlgorithm.getVisitedNodes()));
    }

    /**
     * Method to convert the result to a single string
     * 
     * @param node the result node
     * @param executionTime the execution time
     * @param visitedNodes the number of visited nodes
     * @return the output string
     */
    private String printResult(Node node, long executionTime, int visitedNodes) {
        String output;

        if (node == null) {
            output = "Solusi tidak ditemukan.\n";
        } else {
            output = "Panjang jalur: " + (node.getPath().size()) + "\n";

            output += "Jalur:\n";
            for (int i = 0; i < node.getPath().size(); i++) {
                output += "   " + (i + 1) + ". " + node.getPath().get(i).toLowerCase() + "\n";
            }
            output += "   " + (node.getPath().size() + 1) + ". " + node.getWord().toLowerCase() + "\n";
        }
        
        output += "Banyak node dikunjungi  : " + visitedNodes + " node\n";
            
        if (executionTime > 1000) {
            output += "Waktu eksekusi          : " + executionTime / 1000 + " s " + executionTime % 1000 + " ms\n";
        } else {
            output += "Waktu eksekusi          : " + executionTime + " ms\n";
        }
        return output;
    }
}