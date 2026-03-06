import java.io.File;
import java.io.FileReader;
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
        String currentWord;

        StringBuilder sentence = new StringBuilder();
        Random r = new Random();

        // TODO: clean up this code when you're more awake.
        do {
            randomWordIndex = r.nextInt((words.get(BEGINS_SENTENCE)).size() + 1);
            currentWord = (words.get(BEGINS_SENTENCE)).get(randomWordIndex);

            sentence.append(" ").append(currentWord);
        } while(!endsWithPunctuation(currentWord));

        return sentence.toString();
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
        return null;
    }

    void addWord(String word){
        if(endsWithPunctuation(prevWord)) {
            // current word is added under the BEGINS_SENTENCE key in the words hashmap.
            words.get(BEGINS_SENTENCE).add(word);
        } else if (!endsWithPunctuation(prevWord)) {
            if(words.containsKey(words.get(prevWord))) {
                // if the previous word is present as in a key, or as a key, add current word to the prev key's array list
              words.get(prevWord).add(word);
            } else if (!words.containsKey(words.get(prevWord))) {
                words.put(word, new ArrayList<String>());
            }
        }

        prevWord = word;
    }

    String randomWord(String wordKey) {
        // utilize the key to get an arraylist of words from the hashmap.
//        ArrayList<String> wordsList = words.get(wordKey);

        Random r = new Random();
        ArrayList<String> test = words.get(wordKey);
        try {
            int testInt = test.size();
        }
        catch (Exception e) {
            return null;
        }

        int randomWordIndex = r.nextInt(words.get(wordKey).size());
        // generate a random location, then get that word and return it.

        return words.get(wordKey).get(randomWordIndex);
    }


    void addLine(String newLine) {
            if(newLine.isEmpty()) {
                return;
            }
            String[] seperated = newLine.split(" ");
            for(String s :seperated) {
                addWord(s);
            }
    }

    public static boolean endsWithPunctuation(String word) {
      // TODO: clean up this code when ur more awake.
        try {
            // run over all punctuation options, then if they equal the end of given word return true.
            for(int i = 0; i < PUNCTUATION_MARKS.length(); i++ ){
                if (PUNCTUATION_MARKS.charAt(i) == word.charAt(word.length()-1)) {
                    return true;
                }
            }

            // if nothing at the end of our word matches possible punctuation marks, return false.
            return false;
        }
        catch(Exception e) {
            System.out.println("An error occurred. Word: " + word);
            return false;
        }
    }
}
