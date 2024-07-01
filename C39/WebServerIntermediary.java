package C39;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.Executors;

public class WebServerIntermediary {
    private static final String SERVICE_ENDPOINT = "/servicios";
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServerIntermediary webServer = new WebServerIntermediary(serverPort);
        webServer.startServer();

        System.out.println("Servidor Intermediario escuchando en el puerto " + serverPort);
    }

    public WebServerIntermediary(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        HttpContext serviceContext = server.createContext(SERVICE_ENDPOINT);
        serviceContext.setHandler(this::handleServiceRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleServiceRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String queryString = exchange.getRequestURI().getQuery();
        String[] params = queryString.split("&");
        int numero = Integer.parseInt(params[0].split("=")[1]);
        int servicio = Integer.parseInt(params[1].split("=")[1]);

        String serviceUrl;
        if (servicio == 1) {
            serviceUrl = "http://localhost:8081/sumafibonacci?n=" + numero;
        } else if (servicio == 2) {
            serviceUrl = "http://localhost:8082/evensum?n=" + numero;
        } else {
            String response = "Servicio no válido";
            sendResponse(response.getBytes(), exchange);
            return;
        }

        URL url = new URL(serviceUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream responseStream = conn.getInputStream();
        byte[] responseBytes = responseStream.readAllBytes();
        sendResponse(responseBytes, exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
