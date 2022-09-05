import resource_comparator.ResourceComparatorStandardLibrary;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ResourceComparatorStandardLibrary resourceComparator = new ResourceComparatorStandardLibrary();
        resourceComparator.resourcesComparison("src/main/resources/input.txt",
                "src/main/resources/output.txt");
    }
}