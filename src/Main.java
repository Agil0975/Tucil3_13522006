package src;

import java.util.*;

/*
 *  Class Main
 *  This class is used to run the program
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("||============================================||");
        System.out.println("||     Selamat Datang Word Ladder Solver!     ||");
        System.out.println("||============================================||");
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary("words_alpha.txt");

        System.out.println();
        System.out.println("==================== Input ====================");
        
        // input start and goal word
        String start = null;
        String goal = null;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("Masukkan kata awal    : ");
            start = scanner.nextLine();
            System.out.print("Masukkan kata tujuan  : ");
            goal = scanner.nextLine();

            if (start.length() == goal.length()) {
                if (dictionary.isWordInDictionary(start)) {
                    if (dictionary.isWordInDictionary(goal)) {
                        isValid = true;
                    } 
                    else {
                        System.out.println("Kata tujuan tidak ada di dalam kamus.");
                    }
                } 
                else {
                    System.out.println("Kata awal tidak ada di dalam kamus.");
                }
            } 
            else {
                System.out.println("Kata awal dan kata tujuan harus memiliki panjang yang sama.");
            }
        }
        
        // input algorithm choice
        System.out.println();
        System.out.println("Masukkan algoritma yang ingin digunakan: ");
        System.out.println("    1. Uniform Cost Search Algorithm");
        System.out.println("    2. Greedy Best First Search Algorithm");
        System.out.println("    3. A* Algorithm");
        String choice = null;
        isValid = false;
        while (isValid == false) {
            System.out.print("Pilihan: ");
            choice = scanner.nextLine();
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                isValid = true;
            } 
            else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();

        // run the search algorithm
        SearchAlgorithm searchAlgorithm = null;
        switch (choice) {
            case "1":
                searchAlgorithm = new UCS(start, goal, dictionary);
                break;
            case "2":
                searchAlgorithm = new GreedyBFS(start, goal, dictionary);
                break;
            case "3":
                searchAlgorithm =  new AStar(start, goal, dictionary);
                break;
        }
        searchAlgorithm.search();

        // print the result
        searchAlgorithm.printResult();
    }
}