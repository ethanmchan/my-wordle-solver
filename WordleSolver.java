import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordleSolver {
    private int numTurns;
    private boolean solved;
    private String word;
    private String color;
    private String[] sacredLetters = new String[5];
    private static ArrayList<String> wordList = new ArrayList<String>();

    public WordleSolver() {  //make method for checking statistics (avg # of tries) for "crane" and "amass"
        numTurns = 0;
        solved = false;
        word = "";
        color = "";
        for(int i = 0; i < sacredLetters.length; i++) {
            sacredLetters[i] = "";
        }
    }

    public void run() throws IOException {
        introduction();
        startSolver();
    }

    public void introduction() {
        System.out.println("Hello, Welcome to Wordle Solver!\nThis program will find optimal words to help you solve the daily Wordle!");
        System.out.println("To use Wordle Solver, input the words you put for Wordle and then input the colors that correspond to each letter.");
        System.out.println("(G = Green, Y = Yellow, B = Black)");
    }

    public void startSolver() throws IOException { 
        buildArrayList("fiveLetterWords.txt");
        numTurns = 1;
        solved = false;
        Scanner scanner = new Scanner(System.in);

        while(numTurns <= 6 && solved == false)
        {
            System.out.print("\nEnter your #" + numTurns + " word for Wordle: ");
            word = scanner.nextLine();
            while(!(inDictionary(word))) {
                System.out.println("Your word is suboptimal or is not valid.\n");
                System.out.print("\nEnter your #" + numTurns + " word for Wordle: ");
                word = scanner.nextLine();
            }
            System.out.print("\nEnter the colors for each letter of your #" + numTurns + " word (e.g. YBGBB): ");
            color = scanner.nextLine();
            while(!(isValidColor(color))) {
                System.out.println("Your color input is not valid.\n");
                System.out.print("\nEnter the colors for each letter of your #" + numTurns + " word (e.g. YBGBB): ");
                color = scanner.nextLine();
            }
            if(word.equals("xxxxx")) {
                System.exit(0);
            }
            process(word, color);
            numTurns++;
        }
    }

    public void process(String word, String color) {
        word = word.toLowerCase();
        color = color.toLowerCase();

        for(int i = 0; i < 5; i++) {
            if(color.charAt(i) == 'g') 
            {
                sacredLetters[i] = word.substring(i, i + 1);
                for(int j = 0; j < wordList.size(); j++) {
                    if(wordList.get(j).charAt(i) != word.charAt(i)) {
                        wordList.remove(j);
                        j--;
                    }
                }
            }
            if(color.charAt(i) == 'y') {
                if(hasDuplicate(word, word.substring(i, i + 1))) {
                    sacredLetters[i] = word.substring(i, i + 1);
                }
                for(int j = 0; j < wordList.size(); j++) {
                    if(!(wordList.get(j).contains(word.substring(i, i + 1))) || (wordList.get(j).charAt(i) == word.charAt(i))) {
                        wordList.remove(j);
                        j--;
                    }
                }
            }
            if(color.charAt(i) == 'b') {
                if(!(isSacredLetter(word.substring(i, i + 1)))) {
                    for(int j = 0; j < wordList.size(); j++) {
                        if(wordList.get(j).contains(word.substring(i, i + 1))) {
                            wordList.remove(j);
                            j--;
                        }
                    }
                }
                else {
                    for(int j = 0; j < wordList.size(); j++) {
                        if(hasDuplicate(wordList.get(j), word.substring(i, i + 1))) {
                            wordList.remove(j);
                            j--;
                        }
                    }
                }
            }
        }

        if(wordList.size() == 0)
        {
            System.out.println("This program sucks.");
            System.exit(0);
        }
        if(wordList.size() == 1) {
            System.out.println("The daily wordle is " + wordList.get(0) + ".");
            solved = true;
        }
        else {
            if(numTurns == 1) {   
                for(int i = 0; i < wordList.size(); i++) {
                    if(!containsDuplicateLetter(wordList.get(i))) {
                        System.out.println("The optimal word for the next guess is: " + wordList.get(i) + ".");
                        return;
                    }
                }
                System.out.println("The optimal word for the next guess is: " + wordList.get(0) + ".");
            }
            else {
                System.out.println("The optimal word for the next guess is: " + wordList.get(0) + ".");
            }
        }
    }

    public boolean isSacredLetter(String s) {
        for(int i = 0; i < sacredLetters.length; i++) {
            if(sacredLetters[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicateLetter(String word) {
        String result = "";

        for(int i = 0; i < word.length(); i++) {
            if(!result.contains(String.valueOf(word.charAt(i)))) {
                result += String.valueOf(word.charAt(i));
            }
        }

        if(word.length() > result.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasDuplicate(String word, String letter) {
        int tmpInt = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.substring(i, i + 1).equals(letter)) {
                tmpInt++;
            }
        }
        return tmpInt >= 2;
    }

    public boolean inDictionary(String s) {
        s = s.toLowerCase();
        boolean inDictionary = false;
        for(int i = 0; i < wordList.size(); i++) {
            if(s.equals(wordList.get(i))) {
                inDictionary = true;
            }
        }
        return inDictionary || s.equals("xxxxx");
    }

    public boolean isValidColor(String s) {
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i++) {
            if(!(s.charAt(i) == 'g')) {
                if(!(s.charAt(i) == 'y')) {
                    if(!(s.charAt(i) == 'b')) {
                        return false;
                    }
                }
            }
        }
        return s.length() == 5;
    }

    public static void buildArrayList(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        // read file line by line
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            wordList.add(line);
        }
        // close resources
        br.close();
        fr.close();
    }

    public static void printArrayList() {
        if(wordList.isEmpty()) {
            System.out.println("The list is empty.");
        }
        for(int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }

    public static void main(String args[]) throws IOException {
        WordleSolver solver = new WordleSolver();
        solver.run();
    }
}
