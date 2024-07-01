package ACT2;

public class E4 {
    public static void main(String[] args) {
        int numeros[][] = new int[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numeros[i][j] = Integer.parseInt(args[index]);
                index++;
            }
        }
        // Impresion de la matriz de 3 x 3
        imprimirMatriz(numeros);
        // Matriz de 4x4
        int numeros2[][] = new int[4][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numeros2[i][j] = numeros[i][j];
            }
        }
        // Copiar los elementos de la matriz de 3x3 a la matriz de 4x4
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numeros2[i][j] = numeros[i][j];
            }
        }
        // Inicializar la última columna y el último renglón de la matriz de 4x4
        for (int i = 0; i < 3; i++) {
            numeros2[i][3] = 0; // Inicializar la última columna
            numeros2[3][i] = 0; // Inicializar el último renglón
        }
        // Calcular las sumas de cada fila y cada columna y colocarlas en la última
        // columna y último renglón
        for (int i = 0; i < 3; i++) {
            int sumaFila = 0;
            for (int j = 0; j < 3; j++) {
                numeros2[i][3] += numeros2[i][j]; // Sumar elementos de la fila y guardar en la última columna
                sumaFila += numeros2[j][i]; // Calcular suma de columna
            }
            numeros2[3][i] = sumaFila; // Guardar suma de columna en el último renglón
        }
        System.out.println("\nMatriz de 4x4 con sumas de filas y columnas:");
        imprimirMatriz(numeros2);
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
}