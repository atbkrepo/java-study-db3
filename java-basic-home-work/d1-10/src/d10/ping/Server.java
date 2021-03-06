package d10.ping;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException {


        System.out.println("Start Listen...");
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while (true) {

                try (Socket socket = serverSocket.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {
                    while (true) {
                        writer.write("OK:" + reader.readLine() + '\n');
                        writer.flush();
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }//while
        }

    }
}