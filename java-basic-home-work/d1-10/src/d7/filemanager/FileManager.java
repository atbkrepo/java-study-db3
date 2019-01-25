package d7.filemanager;

import java.io.*;

public class FileManager {
    private static void checkPathIfNull(String path) throws FileNotFoundException {
        if (path == null||path.isEmpty()) {
            throw new FileNotFoundException("Path cant be blank");
        }
    }

    private static void copyFile(File from, File to) throws IOException {
        InputStream inputStream = new FileInputStream(from);
        OutputStream outputStream = new FileOutputStream(to);

        byte[] buffer = new byte[1024];
        int count = 0;
        try {
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }

    }

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) throws FileNotFoundException {
        checkPathIfNull(path);

        File file = new File(path);
        int filesCount = 0;
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                filesCount += countFiles(subFile.getAbsolutePath());
            } else {
                filesCount++;
            }
        }
        return filesCount;
    }

    // public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) throws FileNotFoundException {
        checkPathIfNull(path);

        File file = new File(path);
        int dirsCount = 0;

        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                dirsCount += countDirs(subFile.getAbsolutePath()) + 1;
            }
        }

        return dirsCount;
    }

    private static void copyOrMove(String from, String to, boolean deleteAfter) throws IOException {
        checkPathIfNull(from);
        checkPathIfNull(to);

        File fromDest = new File(from);
        if (!fromDest.exists()) {
            throw new IOException("Param 'from' should point to existed file or directory");
        }
        File toDest = new File(to);
        if (!toDest.exists() && !toDest.isDirectory()) {
            throw new IOException("Param 'to' should be path to existed directory");
        }

        for (File subFile : fromDest.listFiles()) {
            File newFSO = new File(toDest.getAbsolutePath() + "/" + subFile.getName());
            if (subFile.isDirectory()) {
                //create dest dir
                newFSO.mkdir();
                copyOrMove(subFile.getAbsolutePath(), newFSO.getAbsolutePath(),deleteAfter);
            } else {
                copyFile(subFile, newFSO);
            }
            if (deleteAfter) {
                if (!subFile.delete()) {
                    System.out.println("fail to delete:" + subFile.getName());
                }
            }
        }


    }

    /**
     * метод по копированию папок и файлов.
     * Параметр from - путь к файлу или папке,
     * параметр to - путь к папке куда будет производиться копирование.
     */
    public static void copy(String from, String to) throws IOException {
        copyOrMove(from,to,false);
    }

    /**
     * метод по перемещению папок и файлов.
     * Параметр from - путь к файлу или папке,
     * параметр to - путь к папке куда будет производиться копирование.
     */
    public static void move(String from, String to) throws IOException {
        copyOrMove(from,to,true);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("===================================");
        String path = "/home/hl/Downloads";
        System.out.println("Dir:" + path);
        System.out.println("Dirs count: " + FileManager.countDirs(path));
        System.out.println("Files count: " + FileManager.countFiles(path));
        System.out.println("===================================");

        //copy test
        String fromPathCopy = "/home/hl/Downloads/";
        String toPathCopy = "/home/hl/DownloadsCopy/";
        //copy(fromPathCopy, toPathCopy);

        System.out.println("Dir:" + toPathCopy);
        System.out.println("Dirs count: " + FileManager.countDirs(toPathCopy));
        System.out.println("Files count: " + FileManager.countFiles(toPathCopy));
        System.out.println("===================================");

        //move test
        String fromPathMove = "/home/hl/DownloadsCopy/";
        String toPathMove = "/home/hl/DownloadsMove/";
        move(fromPathMove, toPathMove);
        System.out.println("Dir:" + toPathMove);
        System.out.println("Dirs count: " + FileManager.countDirs(toPathMove));
        System.out.println("Files count: " + FileManager.countFiles(toPathMove));
        System.out.println("===================================");
    }
}