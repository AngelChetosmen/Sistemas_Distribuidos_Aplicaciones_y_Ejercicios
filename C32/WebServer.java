package C32;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Clase WebServer que recibe objetos serializados y los deserializa.
 */
public class WebServer {
    public static void main(String[] args) throws IOException {
        // Crear un servidor HTTP escuchando en el puerto 8080.
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Crear un contexto para manejar las solicitudes en el endpoint /objeto.
        server.createContext("/objeto", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                // Procesar solo solicitudes POST.
                if ("POST".equals(exchange.getRequestMethod())) {
                    // Leer los datos del cuerpo de la solicitud.
                    InputStream requestBody = exchange.getRequestBody();
                    byte[] serializedData = readAllBytes(requestBody);

                    // Deserializar el objeto recibido.
                    Demo receivedObject = (Demo) SerializationUtils.deserialize(serializedData);

                    // Imprimir los detalles del objeto deserializado.
                    System.out.println("Objeto deserializado recibido:");
                    System.out.println("a = " + receivedObject.a);
                    System.out.println("b = " + receivedObject.b);

                    // Enviar una respuesta indicando que el objeto fue recibido correctamente.
                    String response = "Objeto recibido correctamente";
                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    // Enviar un código de estado 405 si el método no es POST.
                    exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
                }
            }

            // Método auxiliar para leer todos los bytes de un InputStream.
            private byte[] readAllBytes(InputStream inputStream) throws IOException {
                return inputStream.readAllBytes();
            }
        });

        // Iniciar el servidor.
        server.start();
        System.out.println("Server started on port 8080");
    }
}