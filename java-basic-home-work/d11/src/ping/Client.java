package ping;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//
public class Client {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("127.0.0.1", 8080);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String message = scanner.nextLine();
                writer.write(message + '\n');
                writer.flush();
                String recivedMessage = reader.readLine();
                System.out.println(recivedMessage);
            }
        }

    }
}