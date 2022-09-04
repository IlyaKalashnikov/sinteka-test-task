import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        int firstCounter = Integer.valueOf(bufferedReader.readLine());
        List<String> firstFile = new ArrayList<>();
        for (int i = 0; i < firstCounter; i++) {
            firstFile.add(bufferedReader.readLine());
        }
        int secCounter = Integer.valueOf(bufferedReader.readLine());
        List<String> secFile = new ArrayList<>();
        for (int i = 0; i < secCounter; i++) {
            secFile.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/output.txt"));
        List<String> output = new ArrayList<>();
        if (firstCounter > secCounter) {
            for (int i = 0; i < firstCounter; i++) {
                String firstFileLine = firstFile.get(i);
                bufferedWriter.write(containsCharSeq(firstFileLine, secFile));
                bufferedWriter.newLine();
            }
        } else if (firstCounter < secCounter){
            for (int i = 0; i < secCounter; i++) {
                String secFileLine = secFile.get(i);
                bufferedWriter.write(containsCharSeq(secFileLine, firstFile));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }

    public static String containsCharSeq(String firstFileLine, List<String> secondFile) {
        //checking whether secFile contains any string or not
        if (secondFile.isEmpty()) {
            return firstFileLine + ":?";
        }
        //bringing to UpperCase to make comparison case-insensitive
        String[] firstFileLineStringArray = firstFileLine.toUpperCase().split(" ");
        String secondFileLine = new String();
        for (int i = 0; i < secondFile.size(); i++) {
            secondFileLine = secondFile.get(i);
            String[] secFileLineStringArray = secondFileLine.toUpperCase().split(" ");
            //splitting lines to several arrays
            if (firstFileLineStringArray.length > secFileLineStringArray.length) {
                //comparing word by word
                for (int j = 0; j < firstFileLineStringArray.length; j++) {
                    String word = firstFileLineStringArray[j];
                    //checking if the word is pretext or preposition
                    if (word.length() <= 3) {
                        continue;
                    }
                    for (int n = 0; n < secFileLineStringArray.length; n++) {
                        String wordToCompare = secFileLineStringArray[n];
                        //checking if the wordToCompare is pretext or preposition
                        if (wordToCompare.length() <= 3) {
                            continue;
                        }
                        int comparisonResult = word.compareTo(wordToCompare);
                        //words are equal
                        if (comparisonResult == 0) {
                            //deleting similar line not to compare next time
                            secondFile.remove(i);
                            return firstFileLine + ":" + secondFileLine;
                        } else {
                            //word is longer
                            int x = wordToCompare.length();
                            int substring = x / 2;
                            if (word.contains(wordToCompare.substring(0, substring))) {
                                //deleting similar line not to compare next time
                                secondFile.remove(i);
                                return firstFileLine + ":" + secondFileLine;
                            } else if (word.contains(wordToCompare.substring(substring))) {
                                //deleting similar line not to compare next time
                                secondFile.remove(i);
                                return firstFileLine + ":" + secondFileLine;
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < secFileLineStringArray.length; j++) {
                    String word = secFileLineStringArray[j];
                    if (word.length() <= 3) {
                        continue;
                    }
                    for (int n = 0; n < firstFileLineStringArray.length; n++) {
                        String wordToCompare = firstFileLineStringArray[n];
                        if (wordToCompare.length() <= 3) {
                            continue;
                        }
                        int comparisonResult = word.compareTo(wordToCompare);
                        if (comparisonResult == 0) {
                            //deleting similar line not to compare next time
                            secondFile.remove(i);
                            return firstFileLine + ":" + secondFileLine;
                        } else if (comparisonResult > 0) {
                            //word is longer
                            int x = wordToCompare.length();
                            int substring = x / 2;
                            if (word.contains(wordToCompare.substring(0, substring))) {
                                //deleting similar line not to compare next time
                                secondFile.remove(i);
                                return firstFileLine + ":" + secondFileLine;
                            } else if (word.contains(wordToCompare.substring(substring))) {
                                //deleting similar line not to compare next time
                                secondFile.remove(i);
                                return firstFileLine + ":" + secondFileLine;
                            }
                        }
                    }
                }
            }
        }
        return firstFileLine + ":?";
    }
}
