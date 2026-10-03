import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import itsc2214.*;

/**
 * Reads a document, stores its words, and tracks misspelled words
 * using a dictionary.
 */
public class Project2 extends Document {

    /** Words shorter than this are ignored. */
    private static final int MIN_WORD_LENGTH = 3;

    /** File name. */
    private String fileName;

    /** All words in the document, in order. */
    private ListADT<String> docWords;
    /** Misspelled words (duplicates allowed). */
    private BagADT<String> misspelledWords;
    /** Valid words. */
    private SetADT<String> dictionary;
    /** Distinct words seen in the document. */
    private SetADT<String> uniqueWords;

    /**
     * Creates an empty document and loads the dictionary.
     */
    public Project2() {
        fileName = "";
        docWords = new ListArray<String>();
        misspelledWords = new BagArrayList<String>();
        dictionary = new SetArrayList<String>();
        uniqueWords = new SetArrayList<String>();
        loadDictionary("dictionary.txt");
    }

    /**
     * Reads a dictionary file (one word per line) into the dictionary set.
     * A missing file leaves the dictionary empty.
     *
     * @param dictName name of the dictionary file
     * @return true if loaded, false otherwise
     */
    boolean loadDictionary(String dictName) {
        try {
            for (String line : Files.readAllLines(Paths.get(dictName))) {
                dictionary.add(line.trim().toLowerCase());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns the file name, or an empty string if none.
     *
     * @return the name of the file used
     */
    @Override
    public String getFilename() {
        return fileName;
    }

    /**
     * Loads a file and processes it with loadFromString(). A missing file
     * behaves like an empty file.
     *
     * @param fileName is the name of the file to read in
     * @return true if the file is processed correctly, false otherwise.
     */
    public boolean loadFromFile(String fileName) {
        try {
            this.fileName = fileName;
            java.util.List<String> lines = Files.readAllLines(Paths.get(fileName));
            StringBuilder k = new StringBuilder();
            for (String line : lines) {
                k.append(line).append(" ");
            }
            return loadFromString(k.toString().trim());
        } catch (IOException e) {
            return loadFromString("");
        }
    }

    /**
     * Splits a string on anything that is not a letter and adds each token
     * with addWord().
     *
     * @param data the text to process
     * @return true if processed correctly, false if data is null
     */
    public boolean loadFromString(String data) {
        if (data == null) {
            return false;
        }
        String[] tokens = data.split("[^a-zA-Z]+");
        for (String token : tokens) {
            addWord(token);
        }
        return true;
    }

    /**
     * Adds a word to the document (and to the misspelled bag if it is not in
     * the dictionary). Null words and words shorter than 3 characters are
     * ignored.
     *
     * @param w word to be added
     */
    public void addWord(String w) {
        if (w == null) {
            return;
        }
        String word = w.trim().toLowerCase();
        if (word.length() < MIN_WORD_LENGTH) {
            return;
        }
        docWords.add(word);
        uniqueWords.add(word);
        if (!dictionary.contains(word)) {
            misspelledWords.add(word);
        }
    }

    /**
     * Returns the total number of words, not just unique ones.
     *
     * @return count of total number of words
     */
    public int numWords() {
        return docWords.size();
    }

    /**
     * Returns the number of unique words in the document.
     *
     * @return count of unique words
     */
    public int numUniqueWords() {
        return uniqueWords.size();
    }

    /**
     * Returns the total number of misspelled words, counting repeats.
     *
     * @return count of misspelled words
     */
    public int numMisspelledWords() {
        return misspelledWords.size();
    }

    /**
     * Returns a bag of misspelled words from the document.
     *
     * @return a BagADT with the misspelled words, never null
     */
    public BagADT<String> getMisspelledWords() {
        return misspelledWords;
    }

    /**
     * Not needed for the project but must be implemented.
     *
     * @return string representation of the file.
     */
    public String getContents() {
        return "";
    }

    /**
     * Not needed for the project but must be implemented.
     *
     * @return number of syllables in the document
     */
    public int numOfSyllables() {
        return 0;
    }

    /**
     * Not needed for the project but must be implemented.
     *
     * @param arg0 threshold for syllables to count as polysyllabic
     * @return number of polysyllabic words
     */
    public int numOfPolySyllabic(int arg0) {
        return 0;
    }
}