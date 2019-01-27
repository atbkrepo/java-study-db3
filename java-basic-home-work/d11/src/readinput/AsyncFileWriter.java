package readinput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AsyncFileWriter implements Runnable {
    private String filePath;
    private List<String> buffer;
    private boolean isActive = true;

    public AsyncFileWriter(String filePath, List<String> buffer) {
        this.filePath = filePath;
        this.buffer = buffer;
    }

    public void stop() {
        isActive = false;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                System.out.println("\n[Writing...]");
                for(String line:buffer){
                    writer.write(line);
                    writer.newLine();
                }
                buffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
