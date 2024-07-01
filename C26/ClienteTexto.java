package C26;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class ClienteTexto {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        int currentLineNumber = Integer.parseInt(args[0]);
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/" + currentLineNumber))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                if (!responseBody.isEmpty()) {
                    System.out.println("Línea " + currentLineNumber + ":");
                    System.out.println(responseBody);
                } else {
                    System.out.println("La línea " + currentLineNumber + " está fuera de los límites del archivo.");
                }
            } else {
                System.out.println("Error en la solicitud al servidor: " + response.statusCode());
            }

            System.out.println("Presione 'n' para la siguiente línea, 'p' para la línea previa, o 'q' para salir:");
            String input = scanner.nextLine();

            switch (input) {
                case "n":
                    currentLineNumber++;
                    break;
                case "p":
                    currentLineNumber = Math.max(1, currentLineNumber - 1);
                    break;
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Entrada no reconocida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
