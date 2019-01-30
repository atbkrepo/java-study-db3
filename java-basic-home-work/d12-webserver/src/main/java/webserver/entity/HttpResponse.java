package webserver.entity;

import webserver.exceptions.ServerException;
import webserver.utils.FileUtils;

import java.io.*;
import java.util.StringJoiner;

public class HttpResponse extends HttpHeader {

    private String serverPath;
    private OutputStream outputStream;
    private BufferedWriter outputLineWriter;

    public HttpResponse(HttpHeader request, String serverPath, OutputStream outputStream) {
        super(request.getHttpMethod(), request.getUri(), request.getHttpProtocol(), null);
        this.serverPath = serverPath;
        this.outputStream = outputStream;
        outputLineWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }


    public void prepare() {
        try (
                BufferedInputStream fileStream = FileUtils.getFileStream(serverPath, getUri())
        ) {
            outputLineWriter.write(getHttpProtocol() + " " + HttpResponseCode.OK);
            outputLineWriter.write("\n");
            outputLineWriter.write("\n");
            outputLineWriter.flush();
            int count = 0;
            byte[] buffer = new byte[255];
            while ((count = fileStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            //outputStream.flush();
            //outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServerException(HttpResponseCode.NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException(HttpResponseCode.INTERNAL_SERVER_ERROR);
        }

    }

    public void build() {
        try {
            prepare();
        } catch (ServerException e) {
            try {
                outputLineWriter.write(getHttpProtocol() + " " + e.getErrorType().getValue());
                outputLineWriter.write("\n\n");
                outputLineWriter.flush();
                outputLineWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }


}
