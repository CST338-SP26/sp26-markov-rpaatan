/**
 * @author Rhu Paatan (rpaatan)
 * @version 0.1.0
 * @Since 3/12/26
 *
 * Program Description: Markov assignment, a random sentence generator.
 * Additional Comments: Resubmission for corrections. All code is now done, all tests have passed.
 **/

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Markov {
    private static String BEGINS_SENTENCE = "__$";
    private static String PUNCTUATION_MARKS = ".!?$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    // CONSTRUCTOR(S)
    public Markov() {
        prevWord = BEGINS_SENTENCE;

        words = new HashMap<String, ArrayList<String>>();
        words.put(BEGINS_SENTENCE, new ArrayList<>());
    }

    // GETTER & SETTER METHODS
    HashMap<String, ArrayList<String>> getWords() {
        return words;
    }


    // OTHER METHODS

    public String getSentence() {
        int randomWordIndex;
        String currentWord = randomWord(BEGINS_SENTENCE);
        //[correction] we dont need to create another random word generator, call the randomWord function created earlier.

        StringBuilder sb  = new StringBuilder();

        while(true) {
            sb.append(currentWord);
            // [correction] add the current word to the sentence first, then check if it has punctuation and break if so.
            // previous code wouldn't add a space, or append the current word if it ended with punctuation. current code fixes that.

            if(endsWithPunctuation(currentWord)) {
                break;
            }

            sb.append(" ");
            // add a space

            currentWord = randomWord(currentWord);
            if(currentWord == null) {
                break;
            }
        }

        return sb.toString();
    }

    public void addFromFile(String filename) {
        try {
            File fr = new File(filename);
            Scanner fs = new Scanner(fr);

            while(fs.hasNextLine()) {
                addLine(fs.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File provided generated an error.");
        }
    }

    public String toString(){
        return words.toString();
    }

    void addWord(String word){
        // [correction] fixed code, original addword didn't account for edgecases / null checks.
        // [correction] also fixed structure, this code now adds words no matter what, which caused issues in prior code.
        if(endsWithPunctuation(prevWord) || prevWord == null || BEGINS_SENTENCE.equals(prevWord)) {
            words.get(BEGINS_SENTENCE).add(word);
        } else {
            if (!words.containsKey(prevWord)) {
                words.put(prevWord, new ArrayList<>());
            }
            words.get(prevWord).add(word);
        }

        prevWord = word;
    }

    String randomWord(String wordKey) {
        // utilize the key to get an arraylist of words from the hashmap.
        ArrayList<String> wordsList = words.get(wordKey);

        Random r = new Random();
        int randomWordIndex = r.nextInt(wordsList.size());
        // [correction] +1 placed you out of bounds, removed +1.
        return wordsList.get(randomWordIndex);

    }


    void addLine(String newLine) {
            if(newLine == null || newLine.trim().isEmpty()) {
                // [correction] trim whitespace to prevent errors
                return;
            }
            String[] seperated = newLine.trim().split("\\s+");
                // [correction] rather than using " ", utilized \\s+ to account for other forms of whitespace such as new lines and tabs.
            for(String s :seperated) {
                addWord(s);
            }
    }

    public static boolean endsWithPunctuation(String word) {
        if(word == null || word.isEmpty()) {
            return false;
        }
        // [correction] accounted for nullcase.

        char last = word.charAt(word.length()-1);
        return PUNCTUATION_MARKS.indexOf(last) >= 0;
        // [correction] utilized .indexOf to compare if the word does or does not end with punctuation.
    }
}
