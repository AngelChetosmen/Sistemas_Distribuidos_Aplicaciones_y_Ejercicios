package C26;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer1 {
    private static final String TEXT_FILE = "./C26/El_viejo_y_el_mar.txt";

    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer1 webServer = new WebServer1(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServer1(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.createContext("/", new LineHandler());
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private class LineHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            String[] parts = exchange.getRequestURI().getPath().split("/");
            if (parts.length < 2) {
                exchange.sendResponseHeaders(400, -1);
                return;
            }

            int lineNumber;
            try {
                lineNumber = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                exchange.sendResponseHeaders(400, -1);
                return;
            }

            String line = readLine(lineNumber);
            if (line == null) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            exchange.sendResponseHeaders(200, line.getBytes().length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(line.getBytes());
            responseBody.close();
        }
    }

    private String readLine(int lineNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                if (++currentLine == lineNumber) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}