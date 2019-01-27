package ping;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
//EchoServer (работа с несколькими клиентами параллельно)
public class Server {
    public static void main(String[] args) throws IOException {


        System.out.println("Start Listen...");
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while (true) {

                try {Socket socket = serverSocket.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    AsyncPing ping = new AsyncPing(writer,reader);
                    Thread thread= new Thread(ping);
                    thread.start();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }//while
        }

    }
}