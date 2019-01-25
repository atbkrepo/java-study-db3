package d8.stream;

import java.io.IOException;
import java.io.OutputStream;


// decorator
public class BufferedOutputStream extends OutputStream {
    private OutputStream outputStream;
    private byte[] buffer = new byte[5];
    private int index;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        if (index == buffer.length) {
            flush();
        }
        buffer[index] = (byte) b;
        index++;
    }

    // 8192
    // write byte[] 128
    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len > b.length) {
            throw new IndexOutOfBoundsException();
        }
        int freeSpace = buffer.length - index;
        int size = freeSpace >= len ? len : freeSpace;
        System.arraycopy(b, off, buffer, index, size);
        index =  size;
        if (freeSpace - size == 0) {
            flush();
        }
        if (len - size > 0) {
            write(b, off + size, len - size);
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, index);
        index = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }
}