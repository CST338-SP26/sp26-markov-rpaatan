import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Markov {
    private static String BEGINS_SENTENCE = "__$";
    private static String PUNCTUATION_MARKS = ".!?$";
    private String prevWord;
    private HashMap<String, ArrayList<String>> words;

    // CONSTRUCTOR(S)
    public Markov() {
        ArrayList<String> temp = new ArrayList<String>();
        prevWord = BEGINS_SENTENCE;

        // TODO: Ask about hashmap initialization
        //      -- unsure of how to intialize with parameters as i get continous errors.
       // Presuming it looks like this for now:
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
            FileReader fr = new FileReader(filename);
            // TODO: figure this out. reference past notes.
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
        } else {
            if(words.containsKey(prevWord)) {
                // if the previous word is present as a key, add current word to the prev key's array list
              words.get(prevWord).add(word);
            } else {
                words.put(word, new ArrayList<String>());
            }
        }

        prevWord = word;
    }

    String randomWord(String wordKey) {
        // utilize the key to get an arraylist of words from the hashmap.
        ArrayList<String> wordsList = words.get(wordKey);

        Random r = new Random();
        int randomWordIndex = r.nextInt(wordsList.size() + 1);
        // generate a random location, then get that word and return it.

        return wordsList.get(randomWordIndex);
    }


    void addLine(String newLine) {
        if(newLine.isEmpty()) {
            return;
        }

        // TODO: figure out how to split the string newLine into individual words?
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
