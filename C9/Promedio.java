package C9;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Promedio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese los numeros separados por espacios, "
                    + "si desea salir del programa solo escriba 'salir' en el teclado");
            String entrada = scanner.nextLine();

            // Verificando si el usuario desea salir del programa
            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break; // Salir del bucle
            }

            StringTokenizer tokenizer = new StringTokenizer(entrada);
            int suma = 0;
            int cantidadNumeros = 0;

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                int n = Integer.parseInt(token);
                suma += n;
                cantidadNumeros++;
            }

            try {
                float resultado = (float) suma / cantidadNumeros;
                System.out.println("El promedio de los números ingresados es: " + resultado);
            } catch (ArithmeticException e) {
                System.out.println("Error. No se puede dividir entre cero");
            }
        }
        scanner.close();
    }
}
