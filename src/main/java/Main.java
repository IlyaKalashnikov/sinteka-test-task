import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ResourceComparatorImpl resourceComparator = new ResourceComparatorImpl();
        resourceComparator.resourcesComparison("src/main/resources/input.txt",
                "src/main/resources/output.txt");
    }
}