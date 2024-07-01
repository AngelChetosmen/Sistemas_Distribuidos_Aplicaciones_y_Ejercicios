package C26;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Cliente {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Uso: java HttpClientSynchronous <número de línea>");
            return;
        }

        int lineNumber = Integer.parseInt(args[0]);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("http://localhost:8080/" + lineNumber))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Respuesta del servidor:");
            System.out.println(response.body());
        } else if (response.statusCode() == 404) {
            System.out.println("El servidor no pudo encontrar el número de línea solicitado.");
        } else {
            System.out.println("Error en la solicitud al servidor: " + response.statusCode());
        }
    }
}
