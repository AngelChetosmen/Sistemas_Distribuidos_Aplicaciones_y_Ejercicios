package C39;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class EvenSumServer {
    private static final String EVEN_SUM_ENDPOINT = "/evensum";
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8082; // Usar un puerto diferente al servidor principal
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        EvenSumServer evenSumServer = new EvenSumServer(serverPort);
        evenSumServer.startServer();

        System.out.println("Servidor de Suma de Pares escuchando en el puerto " + serverPort);
    }

    public EvenSumServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        HttpContext evenSumContext = server.createContext(EVEN_SUM_ENDPOINT);
        evenSumContext.setHandler(this::handleEvenSumRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleEvenSumRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String queryString = exchange.getRequestURI().getQuery();
        int n = Integer.parseInt(queryString.split("=")[1]);

        long sum = 0;
        for (int i = 2; i < n; i += 2) {
            sum += i;
        }

        String responseMessage = "La suma de todos los números pares menores que " + n + " es " + sum + "\n";
        sendResponse(responseMessage.getBytes(), exchange);
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
