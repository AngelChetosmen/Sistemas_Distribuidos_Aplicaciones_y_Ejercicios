package C22;

/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;

// Cadenas correspondientes a los endpoints del servidor

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SUMA_FIBONACCI_ENDPOINT = "/sumafibonacci";
    // Variables sencillas.
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        // Instancia objeto de tipo web server
        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    // Constructor

    public WebServer(int port) {
        this.port = port;
    }

    // Método - Instancia de socket TCP a una IP y a un PORT, así como el tamaño de
    // la lista de solicitudes pendientes (En espera)

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);// Decide el sistema
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Mapeo: URI(Uniform Resource Identifier) -> HttpHandler (Interfaz invocada en
        // ejecucuiones HTTP)
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext sumaFibonacciContext = server.createContext(SUMA_FIBONACCI_ENDPOINT);

        // Métodos: Vincula los métodos si es que o se han vinculado ya.

        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        sumaFibonacciContext.setHandler(this::handleSumaFibonacciRequest);
        // Establece un objeto donde establece un pool de 8 hilos para asignarles
        // tareas.

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    // Manejador del Endpoint Task | Argumento encapsulado de la transacción de
    // cliente-servidor.

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        // Recupra del exchange todos los headers con map, una interfaz que se usa para
        // almacenar datos en parejas clave-valor (X-Test)

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        // Información de depuracion enviada de regreso en caso de ser true. Por
        // ejemplo, devolver la cantidad de tiempo del procesamiento.
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        // Método getRequestBody: Recupera toda la información dle cuerpo del mensaje,
        // almacenada en bytes.

        InputStream requestBody = exchange.getRequestBody();
        byte[] requestBytes = readAllBytes(requestBody);
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void handleSumaFibonacciRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        // Obtiene el valor de 'n' del query string
        String queryString = exchange.getRequestURI().getQuery();
        int n = Integer.parseInt(queryString.split("=")[1]);

        // Obtiene el valor del header personalizado "X-Debug"
        Headers headers = exchange.getRequestHeaders();
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        // Calcula la suma de los términos pares de la secuencia de Fibonacci
        long sum = 0;
        long a = 1;
        long b = 2;
        long startTime = System.nanoTime();
        while (b < n) {
            if (b % 2 == 0) {
                sum += b;
            }
            long temp = a + b;
            a = b;
            b = temp;
        }
        long finishTime = System.nanoTime();
        long elapsedTime = finishTime - startTime;

        // Convierte el tiempo total de procesamiento a segundos y milisegundos
        long seconds = elapsedTime / 1_000_000_000;
        long milliseconds = (elapsedTime % 1_000_000_000) / 1_000;

        // Formatea la información del tiempo en una cadena
        String debugMessage = String.format("La operacion tomo %d nanosegundos = %d segundos con %d milisegundos",
                elapsedTime, seconds, milliseconds);

        // Agrega la información del tiempo al encabezado X-debug-info si isDebugMode es
        // true
        if (isDebugMode) {
            exchange.getResponseHeaders().put("X-debug-info", Arrays.asList(debugMessage));
        }

        // Construye la respuesta con la suma de los términos pares de Fibonacci
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

    private byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}