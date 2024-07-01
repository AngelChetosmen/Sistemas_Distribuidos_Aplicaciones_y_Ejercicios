package C10;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ExceptionP {

    public static class ExcepsionPersonalizada extends Exception {
        public ExcepsionPersonalizada(String mensaje) {
            super(mensaje);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese los numeros separados por espacios, "
                    + "si desea salir del programa solo escriba 'salir' en el teclado");

            // Leer la entrada del usuario dentro del bucle
            String entrada = scanner.nextLine();

            // Verificando si el usuario desea salir del programa
            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break; // Salir del bucle
            }

            StringTokenizer tokenizer = new StringTokenizer(entrada);

            int suma = 0;
            int cantidadNumeros = 0;

            if (!tokenizer.hasMoreTokens()) {
                System.out.println("No se han ingresado enteros. Lanzando excepción personalizada.");
                try {
                    throw new ExcepsionPersonalizada("No se han ingresado enteros.");
                } catch (ExcepsionPersonalizada e) {
                    System.out.println("Error: " + e.getMessage());
                    continue; // Continuar con el próximo ciclo
                }
            }

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                try {
                    suma += Integer.parseInt(token);
                    cantidadNumeros++;
                } catch (NumberFormatException e) {
                    System.out.println("Error: No se ingresó un número válido.");
                    // Puedes manejar esta excepción según tus necesidades
                }
            }

            try {
                float promedio = suma / (float) cantidadNumeros; // Convertir uno de los operandos a float
                System.out.println("El promedio de los números ingresados es: " + promedio);
            } catch (ArithmeticException e) {
                System.out.println("Error. No se puede dividir entre cero: " + e.getMessage());
            }
        }
    }
}
