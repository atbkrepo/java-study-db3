package d10.ping;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("Start Listen...");
        Socket socket = serverSocket.accept();
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        byte[] buffer = new byte[120];
        while (true) {
            BufferedInputStream is =  new BufferedInputStream(inputStream);
            int count = is.read(buffer);
            String s = new String(buffer,0,count);
            outputStream.write(("OK="+s).getBytes());
        }

    }
}