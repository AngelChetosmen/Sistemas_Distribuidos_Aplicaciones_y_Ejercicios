package C38;

import java.io.*;
import java.net.InetSocketAddress;
import java.time.ZonedDateTime;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WebServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/objeto", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                if ("POST".equals(exchange.getRequestMethod())) {
                    InputStream requestBody = exchange.getRequestBody();
                    byte[] serializedData = requestBody.readAllBytes();

                    ZonedDateTimeList receivedList = (ZonedDateTimeList) SerializationUtils.deserialize(serializedData);
                    System.out.println("ZonedDateTimeList recibido: " + receivedList.getDateTimes() + "\n");

                    receivedList.addDateTime(ZonedDateTime.now());
                    byte[] responseBytes = SerializationUtils.serialize(receivedList);

                    exchange.sendResponseHeaders(200, responseBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(responseBytes);
                    os.close();
                } else {
                    exchange.sendResponseHeaders(405, -1);
                }
            }
        });

        server.start();
        System.out.println("Server started on port 8080\n");
    }
}
