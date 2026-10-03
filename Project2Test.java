import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Project2 class.
 */
public class Project2Test {

    /** Adding words by hand, including case-insensitivity. */
    @Test
    public void testWordByWord() {
        Project2 runner = new Project2();
        runner.addWord("Hello");
        runner.addWord("HELLO");
        runner.addWord("World");
        assertEquals("numUniqueWords() is wrong", 2, runner.numUniqueWords());
        assertEquals("numWords() is wrong", 3, runner.numWords());
    }

    /** Null, empty, short and padded words. */
    @Test
    public void testAddWordEdgeCases() {
        Project2 runner = new Project2();
        runner.addWord(null);
        runner.addWord("");
        runner.addWord("  ");
        runner.addWord("ab");
        assertEquals(0, runner.numWords());
        runner.addWord("  abc  ");
        assertEquals(1, runner.numWords());
        assertEquals(1, runner.numUniqueWords());
    }

    /** Misspelled words are counted with repeats. */
    @Test
    public void testMisspelled() {
        Project2 runner = new Project2();
        runner.addWord("zzzqx");
        runner.addWord("ZZZQX");
        assertEquals(2, runner.numMisspelledWords());
        assertEquals(2, runner.getMisspelledWords().size());
        assertEquals(1, runner.numUniqueWords());
    }

    /** Empty document returns an empty, non-null bag. */
    @Test
    public void testEmptyDocument() {
        Project2 runner = new Project2();
        assertNotNull(runner.getMisspelledWords());
        assertEquals(0, runner.getMisspelledWords().size());
        assertEquals(0, runner.numWords());
        assertEquals("", runner.getFilename());
    }

    /** Punctuation, digits and whitespace act as separators. */
    @Test
    public void testLoadFromString() {
        Project2 runner = new Project2();
        assertTrue(runner.loadFromString("Hello, Hello,\tWorld\n123 ab!cde"));
        assertEquals("numWords() is wrong", 4, runner.numWords());
        assertEquals("numUniqueWords() is wrong", 3, runner.numUniqueWords());
    }

    /** Null string is rejected. */
    @Test
    public void testLoadFromStringNull() {
        Project2 runner = new Project2();
        assertFalse(runner.loadFromString(null));
    }

    /** Missing file behaves like an empty file. */
    @Test
    public void testFileNotFound() {
        Project2 runner = new Project2();
        assertTrue(runner.loadFromFile("doesNotExist.txt"));
        assertEquals(0, runner.numWords());
        assertEquals(0, runner.numUniqueWords());
        assertEquals("doesNotExist.txt", runner.getFilename());
    }

    /** Loading a real file; update the numbers after counting by hand. */
    @Test
    public void testLoadFromFile() {
        Project2 runner = new Project2();
        assertTrue(runner.loadFromFile("shortdoc.txt"));
        assertTrue(runner.numWords() >= runner.numUniqueWords());
        assertEquals("shortdoc.txt", runner.getFilename());
        // TODO: assertEquals with your hand-computed counts
    }

    /** Missing dictionary leaves the set empty (everything misspelled). */
    @Test
    public void testLoadDictionaryMissing() {
        Project2 runner = new Project2();
        assertFalse(runner.loadDictionary("nope.txt"));
    }

    /** Unused methods, called for coverage. */
    @Test
    public void testCallingMethodsNotUsed() {
        Project2 runner = new Project2();
        assertEquals(0, runner.numOfPolySyllabic(0));
        assertEquals(0, runner.numOfSyllables());
        assertEquals("", runner.getContents());
    }
}