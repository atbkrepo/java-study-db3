package webserver;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

// 200 OK, 404 Not Found, 500 Internal Server Error
public class ServerTest {

    @Test
    public void testServer() throws IOException, URISyntaxException {
        WebServer webServer = new WebServer();
        webServer.setWebAppPath("resources/webapp");
        webServer.setPort(3000);
        webServer.start();
    }
}
// localhost:3000/index.html -> findFile("resources/webapp/index.html")
// localhost:3000/css/style.css -> findFile("resources/webapp/css/style.css")

