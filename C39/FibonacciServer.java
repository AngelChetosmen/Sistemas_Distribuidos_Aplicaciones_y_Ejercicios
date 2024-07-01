package C39;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class FibonacciServer {
    private static final String SUMA_FIBONACCI_ENDPOINT = "/sumafibonacci";
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8081; // Usar un puerto diferente al servidor principal
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        FibonacciServer fibonacciServer = new FibonacciServer(serverPort);
        fibonacciServer.startServer();

        System.out.println("Servidor de Fibonacci escuchando en el puerto " + serverPort);
    }

    public FibonacciServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        HttpContext sumaFibonacciContext = server.createContext(SUMA_FIBONACCI_ENDPOINT);
        sumaFibonacciContext.setHandler(this::handleSumaFibonacciRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleSumaFibonacciRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String queryString = exchange.getRequestURI().getQuery();
        int n = Integer.parseInt(queryString.split("=")[1]);

        long sum = 0;
        long a = 1;
        long b = 2;
        while (b < n) {
            if (b % 2 == 0) {
                sum += b;
            }
            long temp = a + b;
            a = b;
            b = temp;
        }

        String responseMessage = "La suma de los términos pares de Fibonacci menores que " + n + " es " + sum + "\n";
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
