package src;

import src.dictionary.Dictionary;
import src.gui.WordLadderGUI;

/*
 *  Class Main
 *  This class is used to run the program
 */
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("from_QNA.txt");
        WordLadderGUI gui = new WordLadderGUI(dictionary);
        gui.setVisible(true);
    }

    // compile with: 
    // javac -d bin src/*.java
    
    // run with: 
    // java -cp bin src.Main
}