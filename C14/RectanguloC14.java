package C14;

import java.util.Random;

public class RectanguloC14 {
    public static void main(String[] args) {
        // Verifica que se proporcione un argumento (numero entero al programa)
        if (args.length != 1) {
            System.out.println("Por favor, proporcione un número entero n como argumento.");
            return;
        }
        // Intenta convertir el argumento a un número entero
        try {
            int n = Integer.parseInt(args[0]);
            // Crea un arreglo estático de 2*n objetos Coordenada
            Coordenada[] coordenadas = new Coordenada[2 * n];
            Random rand = new Random();

            for (int i = 0; i < coordenadas.length; i++) {
                // Genera valores de x y y aleatorios entre 0 y 1000
                int x = rand.nextInt(1001);
                int y = rand.nextInt(1001);
                coordenadas[i] = new Coordenada(x, y);
            }
            // Crea un rectangulo estático de n objetos Rectangulo
            Rectangulo[] rectangulos = new Rectangulo[n];
            // Inicializa los objetos Rectangulo con las Coordenadas
            for (int i = 0; i < n; i++) {
                rectangulos[i] = new Rectangulo(coordenadas[i], coordenadas[i + n]);
            }
            // Imprime el arreglo de objetos Rectangulo
            for (Rectangulo rectangulo : rectangulos) {
                System.out.println(rectangulo);
                System.out.println("Área del rectángulo: " + rectangulo.area());
                System.out.println("Perímetro del rectángulo: " + rectangulo.imprimePerimetro());
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, proporcione un número entero válido como argumento.");
        }
    }
}
