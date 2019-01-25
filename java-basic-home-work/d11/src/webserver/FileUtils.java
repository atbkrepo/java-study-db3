package webserver;

import java.io.*;

public class FileUtils {

    public static InputStream getFileStream(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                return new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                return null;
            }
        } else {
            //throw new IllegalArgumentException("File Not exists under the path specified [param 1]:" + fileName);
            return null;
        }
    }
}
