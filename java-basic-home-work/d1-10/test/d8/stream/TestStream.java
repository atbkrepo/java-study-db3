package d8.stream;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TestStream {
    private final static String CONTENT = "Hello world";
    private String fileUri;

    private static void writeToFile(String path, String content) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);

            byte[] array = content.getBytes();
            for (byte value : array) {
                try {
                    outputStream.write(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String readFromFile(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int count;
            while((count = inputStream.read(buffer))!=-1){
                String value = new String(buffer, 0, count);
                builder.append(value);
            }
            return  builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    static void writeArray(OutputStream outputStream, String content) throws IOException {
            outputStream.write(content.getBytes());
    }

    static void write(OutputStream outputStream, String content) throws IOException {
        for (byte b : content.getBytes()) {
            outputStream.write(b);
        }

    }

    static void read(InputStream inputStream) throws IOException {
        int value;
        while ((value = inputStream.read()) != -1) {
            System.out.print((char) value);
        }
    }

    private String createFile(boolean withData) {
        try {
            File file = File.createTempFile("test", ".log");

            this.fileUri = file.getAbsolutePath();
            if(withData){
                writeToFile(this.fileUri, CONTENT);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.fileUri;
    }

    @Test
    public void testOutputStreamByte() throws IOException {
        createFile(false);
        FileOutputStream fileOutputStream = new FileOutputStream(this.fileUri);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        try {
            for (byte b : CONTENT.getBytes()) {
                bufferedOutputStream.write(b);
            }
            bufferedOutputStream.flush();
            System.out.println("File:"+this.fileUri);
            String s = readFromFile(this.fileUri);
            assertEquals(CONTENT,s);
        } finally {
            bufferedOutputStream.close();
        }
    }

    @Test
    public void testOutputStreamArrayAll() throws IOException {
        createFile(false);
        FileOutputStream fileOutputStream = new FileOutputStream(this.fileUri);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        try {
            bufferedOutputStream.write(CONTENT.getBytes());
            bufferedOutputStream.flush();
            System.out.println("File:"+this.fileUri);
            String s = readFromFile(this.fileUri);
            assertEquals(CONTENT,s);
        } finally {
            bufferedOutputStream.close();
        }
    }

    @Test
    public void testOutputStreamArray() throws IOException {
        int offset = 2;
        createFile(false);
        FileOutputStream fileOutputStream = new FileOutputStream(this.fileUri);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        try {
            bufferedOutputStream.write(CONTENT.getBytes(),offset,CONTENT.length()-offset);
            bufferedOutputStream.flush();
            System.out.println("File:"+this.fileUri);
            String s = readFromFile(this.fileUri);
            assertEquals(CONTENT.substring(offset),s);
        } finally {
            bufferedOutputStream.close();
        }
    }

    //======================================================================
    @Test
    public void testInputStreamByte () throws IOException{
        createFile(true);
        FileInputStream fileInputStream = new FileInputStream(this.fileUri);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try{
            int count=0;
            StringBuilder stringBuilder = new StringBuilder();
            while((count=bufferedInputStream.read())!=-1){
                stringBuilder.append((char)count);
            }
            assertEquals(CONTENT,stringBuilder.toString());
        }finally {
            bufferedInputStream.close();
        }
    }

    @Test
    public void testInputStreamArrayAll() throws IOException{
        createFile(true);
        FileInputStream fileInputStream = new FileInputStream(this.fileUri);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try{
            int count=0;
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[5];
            while((count=bufferedInputStream.read(buffer))!=-1){
                for (int i = 0; i <count ; i++) {
                    stringBuilder.append((char)buffer[i]);
                }

            }
            assertEquals(CONTENT,stringBuilder.toString());
        }finally {
            bufferedInputStream.close();
        }
    }

    @Test
    public void testInputStreamArray() throws IOException{
        int offset = 2;
        createFile(true);
        FileInputStream fileInputStream = new FileInputStream(this.fileUri);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try{
            int count=0;
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[5];
            buffer[0]=35;
            buffer[1]=35;
            while((count=bufferedInputStream.read(buffer,offset,buffer.length-offset))!=-1){
                for (int i = offset; i <count+offset ; i++) {
                    stringBuilder.append((char)buffer[i]);
                }

            }
            //System.out.println(stringBuilder.toString());
            assertEquals(CONTENT,stringBuilder.toString());
        }finally {
            bufferedInputStream.close();
        }
    }


}
