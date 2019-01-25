package webserver;

import java.io.*;
import java.net.*;
import java.util.StringJoiner;

public class WebServer {

    private String webAppPath = "";
    private int port;


    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private HttpHeader parseRequest(String header) {
        return null;
    }

    public InputStream findFile(String uri) {
        InputStream inputStream = null;
        try {
            inputStream = FileUtils.getFileStream(webAppPath + uri);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\nFile Ignored");
        }
        return inputStream;
    }

    public void start() throws IOException, URISyntaxException {
        if (webAppPath.isEmpty() || !new File(webAppPath).exists()) {
            throw new IllegalStateException("server Path not set");
        }
        System.out.println("Start working");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    //read request till empty string
                    String line;
                    StringJoiner lines = new StringJoiner("\n");
                    while (!(line = reader.readLine()).isEmpty()) {
                        lines.add(line);
                    }
                    HttpRequest request = new HttpRequest(lines.toString());
                    HttpResponse response = new HttpResponse(request);
                    String body="";
                    if (HttpMethod.valueOf("GET") == request.getHttpMethod()) {
                        body=response.prepare(findFile(response.getUri().getPath()));
                    }//else if another http method

                    writer.write(body);
                    System.out.println("Finish");
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}