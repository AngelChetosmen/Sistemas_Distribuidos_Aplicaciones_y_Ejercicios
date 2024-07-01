package C20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class PoligonoIrreg {
    private ArrayList<Coordenada> vertices;

    public PoligonoIrreg() {
        vertices = new ArrayList<>();
    }

    public void anadeVertice(Coordenada vertice) {
        vertices.add(vertice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vértices del polígono irregular:\n");
        for (Coordenada vertice : vertices) {
            sb.append(vertice).append(" Magnitud: ").append(vertice.getMagnitud()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PoligonoIrreg poligono = new PoligonoIrreg();

        // Agregando 10 vértices con coordenadas aleatorias entre +100 y -100
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double x = random.nextDouble() * 200 - 100; // Genera valores entre -100 y 100
            double y = random.nextDouble() * 200 - 100; // Genera valores entre -100 y 100
            Coordenada vertice = new Coordenada(x, y);
            poligono.anadeVertice(vertice);
        }

        // Imprimiendo el polígono antes de ordenar
        System.out.println("Vértices del polígono antes de ordenar:");
        System.out.println(poligono);

        // Ordenando los vértices por su magnitud
        Collections.sort(poligono.vertices, new Comparator<Coordenada>() {
            @Override
            public int compare(Coordenada c1, Coordenada c2) {
                return Double.compare(c1.getMagnitud(), c2.getMagnitud());
            }
        });

        // Imprimiendo el polígono después de ordenar
        System.out.println("Vértices del polígono después de ordenar:");
        System.out.println(poligono);
    }
}
