package ACT2;

import java.util.Scanner;
import java.util.Arrays;

public class E3 {
    public static void main(String[] args) {
        int suma = 0;
        int maximo;
        int minimo;
        int numeros[] = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            numeros[i] = Integer.parseInt(args[i]);
            suma += numeros[i];
            System.out.println(numeros[i] + "2 = " + numeros[i] * 2);
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println(numeros[i]);
        }

        Arrays.sort(numeros);
        minimo = numeros[0];
        maximo = numeros[numeros.length - 1];
        System.out.println("El numero minimo es:" + minimo);
        System.out.println("El numero minimo es:" + maximo);
        System.out.println("La suma total es:" + suma);
    }
}