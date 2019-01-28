import webserver.service.WebServer;

import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException {
        WebServer webServer = new WebServer();
        webServer.setWebAppPath("E:\\owner\\gDrive\\JAVA-STUDY\\java-study-db3\\java-basic-home-work\\d12-webserver\\src\\main\\resources\\webapp");
        webServer.setPort(3000);
        webServer.start();
    }
}
// 200 OK, 404 Not Found, 500 Internal Server Error
// localhost:3000/index.html -> findFile("resources/webapp/index.html")
// localhost:3000/css/style.css -> findFile("resources/webapp/css/style.css")
