package d8.stream;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private byte[] buffer = new byte[5];
    private int indexBuffer = 0;
    private int indexStream = 0;


    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {


        if (indexBuffer < indexStream && indexStream != -1) {
            return buffer[indexBuffer++];
        } else {
            if (indexStream == -1) {
                return indexStream;
            } else {
                if ((indexStream = inputStream.read(buffer, 0, buffer.length)) == -1) {
                    return indexStream;
                } else {
                    indexBuffer = 0;
                    return this.read();

                }
            }
        }

    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b,0,b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len > b.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = off; i < off+len; i++) {
            int index = read();

            if (index == -1) {
                return (i-off)==0?-1:(i-off);
            }

            b[i] = (byte) index;
        }
        return len;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}