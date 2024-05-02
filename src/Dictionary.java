package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 *  Class Dictionary
 *  This class is used to store a dictionary of words and some methods to manipulate the dictionary
 */
public class Dictionary {
    /*  
     *  Attributes
     */
    private String[] dictionary;
    private int length;

    /*
     *  Methods
     */

    /**
     * Constructor
     * This constructor reads the words_alpha.txt file and stores the words in the dictionary array
     */
    public Dictionary() {
        String namaFile = "src/words_alpha.txt";
        try {
            Scanner scanner = new Scanner(new File(namaFile));
            // Counting the number of lines in the file
            int count = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            this.length = count;
            scanner.close();

            // Reading the file and storing the words in dictionary array
            this.dictionary = new String[this.length];
            scanner = new Scanner(new File(namaFile));
            for (int i = 0; i < count; i++) {
                dictionary[i] = scanner.nextLine();
            }

            scanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File " + namaFile + " tidak ditemukan.");
        }
    }

    /**
     * Getter
     * 
     * @return dictionary array
     */
    public String[] getWords() {
        return this.dictionary;
    }

    /**
     * Getter
     * 
     * @return length of dictionary array
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Method to check if a word is in dictionary array
     * 
     * @param word word to be checked
     * @return true if the word is in dictionary array, false otherwise
     */
    public boolean isWordInDictionary(String word) {
        for (int i = 0; i < length; i++) {
            if (word.equals(dictionary[i])) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method to calculate the distance between two words
     * The distance is the number of letters that are different between the two words
     * 
     * @param word1
     * @param word2
     * @return number of different letters between word1 and word2
     */
    public int wordDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return -1;
        } 
        else {
            int count = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Method to get all words in dictionary array that are different by one letter with word
     * 
     * @param word
     * @return array of words that are different by one letter with word
     */
    public String[] getAllOneCharDifferenceStrings(String word) {
        String[] result = new String[length];
    
        // Counting the number of words that are different by one letter with word
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (wordDistance(word, dictionary[i]) == 1) {
                result[count] = dictionary[i];
                count++;
            }
        }

        // Trimming the result array
        String[] resultTrimmed = new String[count];
        for (int i = 0; i < count; i++) {
            resultTrimmed[i] = result[i];
        }
        return resultTrimmed;
    }


    /*
     *  Testing
     */
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        String[] words = dictionary.getAllOneCharDifferenceStrings("west");
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println("nestle in dictionary: " + dictionary.isWordInDictionary("nestle"));
        System.out.println("nastle in dictionary: " + dictionary.isWordInDictionary("nastle"));
    }
}
