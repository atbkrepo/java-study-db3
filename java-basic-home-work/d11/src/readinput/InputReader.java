package readinput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*     2. Приложение должно считывать ввод пользователя с клавиатуры и записывать все в ArrayList<String>.
        Каждые 15 секунд содержимое листа должно сбрасываться в файл другим потоком.*/
public class InputReader {


    public static void main(String[] args) throws IOException {
        List<String> userInputText = Collections.synchronizedList(new ArrayList<>());
        String tempFilePath = File.createTempFile("output", ".log").getAbsolutePath();
        System.out.println("Log File: "+tempFilePath);
        AsyncFileWriter fileWriter = new AsyncFileWriter(tempFilePath, userInputText);
        Thread thread = new Thread(fileWriter);
        thread.start();

        try (Scanner scanner = new Scanner(System.in)) {
            String message;
            do {
                message = scanner.nextLine();
                userInputText.add(message);
            } while (!"STOP".equals(message));
        }
        fileWriter.stop();

    }
}
