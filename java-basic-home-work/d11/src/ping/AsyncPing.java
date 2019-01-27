package ping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class AsyncPing implements Runnable {
    BufferedWriter writer;
    BufferedReader reader;

    public AsyncPing(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;
        while(true){
            try {
                if (!(message = reader.readLine()).isEmpty()) {
                    System.out.println("[IN]: "+message);
                    String outMessage = "OK:"+message;
                    writer.write(outMessage);
                    writer.newLine();
                    writer.flush();
                    System.out.println("[OUT]: "+outMessage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
