package C28;

/*Las importaciones que se muestran como propuesta de error, 
se tiene que ejecutar el archivo gson-2.10.1.jar para que funcione correctamente*/
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class ejercicio42 {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        // Realiza tres solicitudes a la API de Breaking Bad Quotes
        for (int i = 0; i < 3; i++) {
            HttpResponse<String> response = sendBreakingBadQuotesRequest();
            String responseBody = response.body();
            // Parsea la respuesta JSON
            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            String quote = jsonObject.get("quote").getAsString();
            String author = jsonObject.get("author").getAsString();
            // Imprime la cita original
            System.out.println("Cita original en inglés:");
            System.out.println("Frase: " + quote);
            System.out.println("Autor: " + author);
            // Traduce la cita a español
            translateQuote(quote);
        }
    }

    private static HttpResponse<String> sendBreakingBadQuotesRequest() throws IOException, InterruptedException {
        // Construye la URL para la API de Breaking Bad Quotes
        String breakingBadApiUrl = "https://api.breakingbadquotes.xyz/v1/quotes";
        // Construye la solicitud GET con la URL
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(breakingBadApiUrl))
                .build();
        // Envía la solicitud GET y obtiene la respuesta
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static void translateQuote(String quote) throws IOException, InterruptedException {
        // API key de Google Cloud Translation (reemplaza tu propia clave)
        String apiKey = "**TU API KEY AQUI**";
        // Codifica el texto a traducir para que pueda ser enviado como parámetro en la
        // URL
        String encodedText = URLEncoder.encode(quote, StandardCharsets.UTF_8);
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
        System.out.println("Cita traducida al español:");
        System.out.println(response.body());
    }
}