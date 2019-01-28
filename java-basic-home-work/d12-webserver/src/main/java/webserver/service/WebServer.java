package webserver.service;

import webserver.entity.*;
import webserver.utils.FileUtils;

import java.io.*;
import java.net.*;
import java.rmi.ServerError;
import java.util.StringJoiner;

import static webserver.entity.HttpResponseCode.BAD_REQUEST;

public class WebServer {

    private String webAppPath = "";
    private int port;

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public void start() throws IOException, URISyntaxException {
        System.out.println("Start working");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {

                    HttpRequest request = new HttpRequest(inputStream);
                    HttpResponse response = new HttpResponse(request,webAppPath,outputStream);

                    if (HttpMethod.valueOf("GET") == request.getHttpMethod()) {
                        response.build();
                    }
                    System.out.println("Finish");
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}