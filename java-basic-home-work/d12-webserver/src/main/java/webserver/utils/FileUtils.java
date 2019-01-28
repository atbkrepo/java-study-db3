package webserver.utils;

import java.io.*;

public class FileUtils {

    public static BufferedInputStream getFileStream(String serverPath, String fileName) throws FileNotFoundException {
        File file = new File(serverPath,fileName);
        return new BufferedInputStream(new FileInputStream(file));
    }
}
