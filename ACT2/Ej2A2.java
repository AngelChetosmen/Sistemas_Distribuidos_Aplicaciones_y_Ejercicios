package ACT2;

public class Ej2A2 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int factorial = 1;

        int i = 1;
        while (i <= n) {
            factorial *= i;
            i++;
        }

        System.out.println("El factorial de " + n + " es " + factorial);
    }
}
