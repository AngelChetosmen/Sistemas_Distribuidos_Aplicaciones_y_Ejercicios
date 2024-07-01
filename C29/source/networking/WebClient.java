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
package networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

// Clase que representa un cliente web simple para hacer solicitudes HTTP.
public class WebClient {
    private HttpClient client;

    // Constructor que inicializa el cliente HTTP con la versión 1.1.
    public WebClient() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    // Método principal para probar solicitudes POST.
    public static void main(String[] args) {
        WebClient webClient = new WebClient();
        String postsEndpoint = "https://jsonplaceholder.typicode.com/posts";

        // JSON para las solicitudes POST con diferentes datos.
        String jsonData1 = "{\"userId\": 1, \"title\": \"7CM1-1\", \"body\": \"Armando Espinosa\"}";
        String jsonData2 = "{\"userId\": 1, \"title\": \"7CM1-1\", \"body\": \"Angel Vega\"}";

        // Realiza las solicitudes POST y maneja las respuestas.
        CompletableFuture<HttpResponse<String>> responseFuture1 = webClient.sendPostRequest(postsEndpoint, jsonData1);
        CompletableFuture<HttpResponse<String>> responseFuture2 = webClient.sendPostRequest(postsEndpoint, jsonData2);

        handleResponse(responseFuture1, "Primer servidor");
        handleResponse(responseFuture2, "Segundo servidor");

        // Espera a que ambas respuestas se completen.
        CompletableFuture.allOf(responseFuture1, responseFuture2).join();
    }

    // Método para enviar solicitudes POST.
    public CompletableFuture<HttpResponse<String>> sendPostRequest(String url, String jsonData) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    // Método auxiliar para manejar respuestas de solicitudes POST.
    private static void handleResponse(CompletableFuture<HttpResponse<String>> responseFuture, String serverName) {
        responseFuture.whenComplete((response, throwable) -> {
            if (throwable != null) {
                System.out.println("Error durante la solicitud al " + serverName + ": " + throwable.getMessage());
            } else {
                System.out.println("Respuesta del " + serverName + ":");
                System.out.println(response.body());
            }
        });
    }

    public CompletableFuture<String> sendTask(String workerAddress, byte[] requestPayload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendTask'");
    }
}
