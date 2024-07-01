package C9;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Promtry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese los numeros separados por espacios: ");
            String entrada = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(entrada);
            int suma = 0;
            try {
                int cantidadNumeros = 0;
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    int n = Integer.parseInt(token);
                    suma += n;
                    cantidadNumeros++;
                }
                System.out.println("El promedio de los números ingresados es: " + (float) suma / cantidadNumeros);
            } catch (ArithmeticException e) {
                System.out.println("Error. Dividir entre cero");
            }

            // Verificar si el usuario desea salir del programa
            System.out.println("¿Desea salir del programa? (escriba 'salir' para salir)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("salir")) {
                break; // Salir del bucle
            }
        }

        scanner.close(); // Cerrar el escáner al salir del bucle
    }
}
