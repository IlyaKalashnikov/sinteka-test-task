package resource_comparator;

import java.io.IOException;
import java.util.List;

public interface ResourceComparator {
    void resourcesComparison(String input, String output) throws IOException;
    String fileComparison(String firstFileLine, List<String> secondFile);
    boolean stringArraysComparison(String[] firstFileLineStringArray, String[] secFileLineStringArray);
    boolean wordComparison(String word, String wordToCompare);
}
