package C32;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class E35 {
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10)).build();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Uso: java HttpClientSynchronouss <n>");
            return;
        }

        // Convertir el argumento a BigInteger para manejar números muy grandes.
        BigInteger n = new BigInteger(args[0]);
        String requestBody = n.toString(); // Convertir BigInteger a String para el cuerpo de la solicitud

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/sumafibonacci"))
                .setHeader("Content-Type", "text/plain")
                .setHeader("User-Agent", "Java 11 HttpClient Bot");

        // Agregar el encabezado de debug si se requiere medir el tiempo de ejecución.
        if (System.getProperty("TestDebug") != null && System.getProperty("TestDebug").equals("true")) {
            requestBuilder.setHeader("Debug", "true");
        }

        HttpRequest fibonacciRequest = requestBuilder.build();

        long startTime = System.currentTimeMillis();
        HttpResponse<String> fibonacciResponse = httpClient.send(fibonacciRequest,
                HttpResponse.BodyHandlers.ofString());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\nRespuesta del servidor:");
        System.out.println(fibonacciResponse.body());
        System.out.println("\nTiempo de procesamiento del servidor: " + duration + " ms");
    }
}
