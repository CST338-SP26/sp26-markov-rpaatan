import java.util.ArrayList;
import java.util.HashMap;

public class Markov {
    private static String BEGINS_SENTENCE = "__$";
    private static String PUNCTUATION_MARKS = ".!?$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    public String getSentence() {
        return null;
    }

    public void addFromFile(String filename) {

    }

    public String toString(){
        return null;
    }

    void addWord(String word){

    }

    String randomWord() {
        return null;
    }

    HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    void addLine(String newLine) {

    }

    public static boolean endsWithPunctuation(String word) {
        return true;
    }
}
