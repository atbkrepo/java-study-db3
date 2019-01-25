package d10.ping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        byte[] buffer = new byte[120];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int count = inputStream.read(buffer);
            System.out.println(new String(buffer, 0, count));

            String message = scanner.nextLine();
            outputStream.write(message.getBytes());
        }
    }
}