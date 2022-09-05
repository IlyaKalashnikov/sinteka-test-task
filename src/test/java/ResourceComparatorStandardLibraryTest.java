import resource_comparator.ResourceComparatorStandardLibrary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceComparatorStandardLibraryTest {
    private final ResourceComparatorStandardLibrary resourceComparator = new ResourceComparatorStandardLibrary();

    @Test
    void fileComparison_shouldReturnTrueForListContainingString() {
        String str = "наковальня";
        List<String> file = new ArrayList<>(List.of("молот", "наковальня", "пласкогубцы"));

        String actual = resourceComparator.fileComparison(str, file);

        assertEquals("наковальня:наковальня", actual);
    }

    @Test
    void fileComparison_shouldReturnFalseForListNotContainingString() {
        String str = "наковальня";
        List<String> file = new ArrayList<>(List.of("молот", "пласкогубцы"));

        String actual = resourceComparator.fileComparison(str, file);

        assertEquals("наковальня:?", actual);
    }

    @Test
    void stringArraysComparison_shouldReturnTrueForSimilarArrays() {
        String [] a = {"ведро", "для", "воды"};
        String [] b = {"корыто", "для", "воды"};

        boolean actual = resourceComparator.stringArraysComparison(a, b);

        assertTrue(actual);
    }

    @Test
    void wordComparison_shouldReturnTrueForDifferentArrays(){
        String [] a = {"Молот"};
        String [] b = {"Наковальня"};

        boolean actual = resourceComparator.stringArraysComparison(a,b);

        assertFalse(actual);
    }

    @Test
    void wordComparison_shouldReturnTrueForIdenticalWords() {
        String a = "Краска";
        String b = "Краска";

        boolean actual = resourceComparator.wordComparison(a,b);

        assertTrue(actual);
    }

    @Test
    void wordComparison_shouldReturnTrueForSimilarWords(){
        String a = "Бетонный";
        String b = "Бетон";

        boolean actual = resourceComparator.wordComparison(a,b);

        assertTrue(actual);
    }

    @Test
    void wordComparison_shouldReturnTrueForDifferentWords(){
        String a = "Молот";
        String b = "Наковальня";

        boolean actual = resourceComparator.wordComparison(a,b);

        assertFalse(actual);
    }

    //That's why it is inefficient to check similarity not using the stemmer
    //We could have been tried to make our own stemmer, but there is one already
//    @Test
//    void wordComparison_shouldReturnTrueForSimilarWords1() {
//        String a = "деревообрабатывающий";
//        String b = "дерево";
//
//        boolean actual = resourceComparator.wordComparison(a, b);
//
//        assertTrue(actual);
//    }
}