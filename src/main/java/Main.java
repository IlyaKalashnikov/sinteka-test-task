import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
//        int firstCounter = Integer.valueOf(bufferedReader.readLine());
//        String[] firstFile = new String[firstCounter];
//        for (int i = 0; i < firstCounter; i++) {
//            firstFile[i] = bufferedReader.readLine();
//        }
//        int secCounter = Integer.valueOf(bufferedReader.readLine());
//        String[] secFile = new String[secCounter];
//        for (int i = 0; i < secCounter; i++) {
//            secFile[i] = bufferedReader.readLine();
//        }
//        List<String> output = new ArrayList<>();
//        if (firstCounter > secCounter) {
//            for (int i = 0; i < firstCounter; i++) {
//                String line = firstFile[i];
//                for (int j = 0; j < secCounter; j++) {
//                    if (line.contains(secFile[j])) {
//                        line += ":" + secFile[j];
//                        output.add(line);
//                        break;
//                    }
//                }
//                line += ":?";
//                output.add(line);
//            }
//        }
        String first = "ведро для воды";
        String second = "корыто для воды";
        System.out.println(containsCharSeq(first, second));
    }

    public static boolean containsCharSeq(String firstFileLine, String secFileLine) {
        //bringing to UpperCase to make comparison case-insensitive
        String[] firstFileLineStringArray = firstFileLine.toUpperCase().split(" ");
        String[] secFileLineStringArray = secFileLine.toUpperCase().split(" ");
        //splitting lines to several arrays
        if (firstFileLineStringArray.length > secFileLineStringArray.length) {
            //comparing word by word
            for (int i = 0; i < firstFileLineStringArray.length; i++) {
                String word = firstFileLineStringArray[i];
                //checking if the word is pretext or preposition
                if (word.length() <= 3){
                    continue;
                }
                for (int j = 0; j < secFileLineStringArray.length; j++) {
                    String wordToCompare = secFileLineStringArray[j];
                    //checking if the wordToCompare is pretext or preposition
                    if (wordToCompare.length() <= 3){
                        continue;
                    }
                    int comparisonResult = word.compareTo(wordToCompare);
                    //words are equal
                    if (comparisonResult == 0) {
                        return true;
                    } else {
                        //word is longer
                        int x = wordToCompare.length();
                        int substring = x / 2;
                        if (word.contains(wordToCompare.substring(0, substring))) {
                            return true;
                        } else if (word.contains(wordToCompare.substring(substring))) {
                            return true;
                        }
                    }
                }
                return false;
            }
        } else {
            for (int i = 0; i < secFileLineStringArray.length; i++) {
                String word = secFileLineStringArray[i];
                if (word.length() <= 3){
                    continue;
                }
                for (int j = 0; j < firstFileLineStringArray.length; j++) {
                    String wordToCompare = firstFileLineStringArray[j];
                    if (wordToCompare.length() <= 3){
                        continue;
                    }
                    int comparisonResult = word.compareTo(wordToCompare);
                    if (comparisonResult == 0) {
                        return true;
                    } else if (comparisonResult > 0) {
                        //word is longer
                        int x = wordToCompare.length();
                        int substring = x / 2;
                        if (word.contains(wordToCompare.substring(0, substring))) {
                            return true;
                        } else if (word.contains(wordToCompare.substring(substring))) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }
}
