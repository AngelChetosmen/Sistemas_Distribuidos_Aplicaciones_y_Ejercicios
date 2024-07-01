package C26;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientSynchronouss {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Uso: java HttpClientSynchronouss <número_de_línea>");
            return;
        }

        int lineNumber = Integer.parseInt(args[0]);
        String requestBody = Integer.toString(lineNumber);

        HttpRequest readLineRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/readLine"))
                .setHeader("Content-Type", "text/plain")
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> readLineResponse = httpClient.send(readLineRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nRespuesta del servidor:");
        System.out.println(readLineResponse.body());
    }
}