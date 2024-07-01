package C28;

//Línea 26, api-key documentada debido a posibles fugas de consulta, por eso marca un error. Quitar comentarios y funciona al 100. 
//También se inhabilito la console.cloud.google.com de la API cloud translate API para que no se "consumiera", hay que vovlerla habiltar para futuros programas.
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class HttpClientSynchronouss {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            System.out.println("Uso: java HttpClientSynchronouss <texto_a_traducir>");
            return;
        }

        String textToTranslate = args[0];
        String apiKey = "AIzaSyAq4w8MRzgOLrNb871E5Cy-j-oYywq-O9Q"; // API key

        // Codifica el texto a traducir para que pueda ser enviado como parámetro en la
        // URL
        String encodedText = URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8);

        // Construye la URL con los parámetros obligatorios y opcionales
        String translationApiUrl = "https://translation.googleapis.com/language/translate/v2"
                + "?target=es"
                + "&key=" + apiKey
                + "&q=" + encodedText;

        // Construye la solicitud POST con la URL y el encabezado Content-Type
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(translationApiUrl))
                .setHeader("Content-Type", "application/json")
                .build();

        // Envía la solicitud POST y obtiene la respuesta
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprime la respuesta obtenida de la API de Cloud Translation
        System.out.println("Respuesta de la API de Cloud Translation:");
        System.out.println(response.body());
    }
}
