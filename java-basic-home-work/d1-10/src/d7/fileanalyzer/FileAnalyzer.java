package d7.fileanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Написать программу FileAnalyzer, которая в консоли принимает 2 параметра:
 * 1) путь к файлу
 * 2) слово
 * Usage:
 * java FileAnalyzer C:/test/story.txt duck
 * <p>
 * Выводит:
 * 1) Кол-во вхождений искомого слова в файле
 * 2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!"), каждое преждложение с новой строки.
 */

public class FileAnalyzer {
    private String path;
    private String searchWord;

    public FileAnalyzer(String path, String searchWord) {
        this.path = path;
        this.searchWord = searchWord;
    }

    private static void checkPathIfNull(String path) throws FileNotFoundException {
        if (path == null || path.isEmpty()) {
            throw new FileNotFoundException("Path cant be blank");
        }
    }


    private String readFile(String path) throws IOException {
        checkPathIfNull(path);
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IOException("Param 'to' should be path to existed file");
        }
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        try {
            while ((count = inputStream.read(buffer)) != -1) {
                String recievedString = new String(buffer, 0, count);
                stringBuilder.append(recievedString);
            }
        } finally {
            inputStream.close();
        }

        return stringBuilder.toString();
    }


    private List<String> parse(String text, String delim) throws IOException {
        char[] textFileArray = text.toCharArray();
        List<String> res = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i < textFileArray.length; i++) {
            if (delim.contains(String.valueOf(textFileArray[i]))) {
                String resStr = String.valueOf(textFileArray, offset, i - offset + 1).trim();
                offset = i + 1;
                res.add(resStr);
            }
        }

        return res;
    }

    public int countOfTheWord() throws IOException {
        String textFile = readFile(this.path);
        List<String> words = parse(textFile, " ,.?!");
        int res = 0;
        for (String word : words) {
            if (word.toUpperCase().contains(this.searchWord.trim().toUpperCase())) {
                res++;
            }

        }
        return res;
    }

    public List<String> sentencesWithTheWord() throws IOException {
        String textFile = readFile(this.path);
        List<String> res = new ArrayList<>();

        List<String> sentances = parse(textFile, ".?!");
        for (String sentance : sentances) {
            for (String word : parse(sentance, " ,.?!")) {
                if (word.toUpperCase().contains(this.searchWord.trim().toUpperCase())) {
                    res.add(sentance);
                    break;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            throw new Exception("Params count shoud be no less then 2");
        }
        FileAnalyzer fileAnalyzer = new FileAnalyzer(args[0], args[1]);
        System.out.println(fileAnalyzer.countOfTheWord());

        for (String s : fileAnalyzer.sentencesWithTheWord()) {
            System.out.println(s);
        }
    }
}
