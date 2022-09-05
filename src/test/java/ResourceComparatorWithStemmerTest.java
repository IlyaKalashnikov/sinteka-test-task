import resource_comparator.ResourceComparatorWithStemmer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceComparatorWithStemmerTest {
    private final ResourceComparatorWithStemmer resourceComparatorWithStemmer =
            new ResourceComparatorWithStemmer();

    //Other methods are tested in ResourceComparatorStandardLibraryTest

    @Test
    void wordComparison_shouldReturnTrueForSimilarWords() {
        String a = "деревообрабатывающий";
        String b = "дерево";

        boolean actual = resourceComparatorWithStemmer.wordComparison(a, b);

        assertTrue(actual);
    }

    //But this stemmer also have some work to do:
//    @Test
//    void wordComparison_shouldReturnTrueForSimilarWords() {
//        String a = "Лакокрасочный";
//        String b = "Краска";
//
//        boolean actual = resourceComparatorWithStemmer.wordComparison(a, b);
//
//        assertTrue(actual);
//    }
}
