package C32;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;

/**
 * Clase HttpClientSynchronouss que envía un objeto serializado a un servidor
 * HTTP y recibe una respuesta.
 */
public class HttpClientSynchronouss {
    // Creación de un cliente HTTP con una configuración de tiempo de espera de
    // conexión.
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        // Crear una instancia del objeto Demo para ser enviado.
        Demo object = new Demo(2024, "Prueba serializacion y deserializacion");

        // Serializar el objeto utilizando la clase SerializationUtils.
        byte[] serializedDemo = SerializationUtils.serialize(object);

        // Imprimir el objeto serializado en forma de array de bytes.
        System.out.println("Objeto serializado byte por byte (-128 a 127):");
        System.out.println(Arrays.toString(serializedDemo));

        // Imprimir los valores del objeto Demo.
        System.out.println("Contenido del objeto a enviar:");
        System.out.println("a = " + object.a);
        System.out.println("b = " + object.b);

        // Crear una solicitud HTTP POST para enviar el objeto serializado.
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(serializedDemo))
                .uri(URI.create("http://localhost:8080/objeto"))
                .setHeader("Content-Type", "application/octet-stream")
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        // Enviar la solicitud y recibir la respuesta del servidor.
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprimir la respuesta recibida del servidor.
        System.out.println("\nRespuesta del servidor:");
        System.out.println(response.body());
    }
}