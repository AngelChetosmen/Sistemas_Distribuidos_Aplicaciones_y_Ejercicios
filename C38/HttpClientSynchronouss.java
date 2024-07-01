package C38;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class HttpClientSynchronouss {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        ZonedDateTimeList dateTimeList = new ZonedDateTimeList();
        dateTimeList.addDateTime(ZonedDateTime.now());

        byte[] serializedDateTimeList = SerializationUtils.serialize(dateTimeList);
        System.out.println("Enviando ZonedDateTimeList serializado...\n");

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(serializedDateTimeList))
                .uri(URI.create("http://localhost:8080/objeto"))
                .setHeader("Content-Type", "application/octet-stream")
                .build();

        HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

        ZonedDateTimeList receivedList = (ZonedDateTimeList) SerializationUtils.deserialize(response.body());
        System.out.println("ZonedDateTimeList recibido: " + receivedList.getDateTimes() + "\n");

        // Ciclo infinito para enviar y recibir
        while (true) {
            Thread.sleep(10000);
            receivedList.addDateTime(ZonedDateTime.now());
            byte[] updatedList = SerializationUtils.serialize(receivedList);

            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofByteArray(updatedList))
                    .uri(URI.create("http://localhost:8080/objeto"))
                    .setHeader("Content-Type", "application/octet-stream")
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
            receivedList = (ZonedDateTimeList) SerializationUtils.deserialize(response.body());
            System.out.println("ZonedDateTimeList actualizado: " + receivedList.getDateTimes() + "\n");
        }
    }
}
