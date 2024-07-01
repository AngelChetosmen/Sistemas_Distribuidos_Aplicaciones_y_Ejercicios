import java.util.Scanner;

public class ej2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un numero entero: ");
        int number = scanner.nextInt();
        String result = (number % 2 == 0) ? "Numero par" : " Numero impar";
        System.out.println("El número " + number + " que ingresado es " + result + ".");
        scanner.close();
    }
}