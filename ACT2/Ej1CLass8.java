package ACT2;

import java.util.Scanner;

public class Ej1CLass8 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("El programa debe recibir exactamente un argumento entero.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Ingresa números enteros. Cuando ingreses un número mayor a " + n + ", el programa terminará.");

        int num = scanner.nextInt();
        while (num <= n) {
            num = scanner.nextInt();
        }

        System.out.println("Terminando el programa...");
        scanner.close();
    }
}
