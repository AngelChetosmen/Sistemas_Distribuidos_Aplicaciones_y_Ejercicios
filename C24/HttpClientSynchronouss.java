package C24;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientSynchronouss {
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10)).build();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Uso: java HttpClientSynchronouss <n>");
            return;
        }
        int n = Integer.parseInt(args[0]); // Tomar el valor de n de los argumentos de la línea de comandos
        String requestBody = Integer.toString(n);
        HttpRequest fibonacciRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/sumafibonacci"))
                .setHeader("Content-Type", "text/plain")
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        long startTime = System.currentTimeMillis();
        HttpResponse<String> fibonacciResponse = httpClient
                .send(fibonacciRequest, HttpResponse.BodyHandlers.ofString());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("\nRespuesta del servidor:");
        System.out.println(fibonacciResponse.body());
        System.out.println("\nTiempo de procesamiento del servidor: " + duration + " ms");
    }
}