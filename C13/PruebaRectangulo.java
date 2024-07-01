package C13;

import java.util.Random;

public class PruebaRectangulo {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Rectangulo[] arrRect = new Rectangulo[n];
        Random ran = new Random();

        Coordenada si = new Coordenada(0, 0);
        Coordenada id = new Coordenada(0, 0);

        for (int i = 0; i < arrRect.length; i++) {
            si.setOrdenada(ran.nextInt(1000));
            si.setAbcisa(ran.nextInt(1000));
            id.setOrdenada(ran.nextInt(1000));
            id.setAbcisa(ran.nextInt(1000));

            arrRect[i] = new Rectangulo(si, id);
        }

        for (int i = 0; i < arrRect.length; i++) {
            System.out.println(arrRect[i]);
        }
    }
}