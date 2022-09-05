import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceComparatorImpl implements ResourceComparator {

    @Override
    public void resourcesComparison(String input, String output) throws IOException {
        int firstCounter;
        int secCounter;
        List<String> firstFile;
        List<String> secFile;

        try (BufferedReader bufferedReader =
                     new BufferedReader
                             (new FileReader("src/main/resources/input.txt"))) {
            firstCounter = Integer.parseInt(bufferedReader.readLine());
            firstFile = new ArrayList<>();
            for (int i = 0; i < firstCounter; i++) {
                firstFile.add(bufferedReader.readLine());
            }
            secCounter = Integer.parseInt(bufferedReader.readLine());
            secFile = new ArrayList<>();
            for (int i = 0; i < secCounter; i++) {
                secFile.add(bufferedReader.readLine());
            }
        }
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter
                             (new FileWriter("src/main/resources/output.txt"))) {
            //this check is to cover this example "Бетон с присадкой:Цемент"
            if (firstCounter == 1 && firstCounter == secCounter) {
                bufferedWriter.write(firstFile.get(0) + ":" + secFile.get(0));
            } else {
                for (int i = 0; i < firstCounter; i++) {
                    String firstFileLine = firstFile.get(i);
                    bufferedWriter.write(fileComparison(firstFileLine, secFile));
                    bufferedWriter.newLine();
                }
                if (!secFile.isEmpty()) {
                    for (String s : secFile) {
                        bufferedWriter.write(s + ":?");
                        bufferedWriter.newLine();
                    }
                }
            }
        }
    }

    private String fileComparison(String firstFileLine, List<String> secondFile) {
        if (secondFile.isEmpty()) {
            return firstFileLine + ":?";
        }
        //bringing to UpperCase to make comparison case-insensitive
        String[] firstFileLineStringArray = firstFileLine.toUpperCase().split(" ");
        String secondFileLine;
        for (int i = 0; i < secondFile.size(); i++) {
            secondFileLine = secondFile.get(i);
            String[] secFileLineStringArray = secondFileLine.toUpperCase().split(" ");
            //comparing string arrays word by word
            if (stringArraysComparison(firstFileLineStringArray, secFileLineStringArray)) {
                secondFile.remove(i);
                return firstFileLine + ":" + secondFileLine;
            }
        }
        return firstFileLine + ":?";
    }

    private boolean stringArraysComparison(String[] firstFileLineStringArray, String[] secFileLineStringArray) {
        for (String word : firstFileLineStringArray) {
            //checking if the word is pretext or preposition
            if (word.length() <= 3) {
                continue;
            }
            for (String wordToCompare : secFileLineStringArray) {
                //checking if the wordToCompare is pretext or preposition
                if (wordToCompare.length() <= 3) {
                    continue;
                }
                if (wordComparison(word, wordToCompare)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean wordComparison(String word, String wordToCompare) {
        int comparison = word.compareTo(wordToCompare);
        if (comparison == 0) {
            return true;
        } else if (comparison > 0) {
            //to imitate real lexical analysis we divide the word by two parts
            //hypothesis is that two similar words would probably have
            //identical word stem or at least identical ending
            int x = word.length();
            int substring = x / 2;
            return wordToCompare.contains(word.substring(0, substring)) ||
                    wordToCompare.contains(word.substring(substring));
        } else if (comparison < 0) {
            int x = wordToCompare.length();
            int substring = x / 2;
            return word.contains(wordToCompare.substring(0, substring)) ||
                    word.contains(wordToCompare.substring(substring));
        }
        return false;
    }
}
