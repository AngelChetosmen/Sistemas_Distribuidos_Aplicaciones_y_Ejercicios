package C13;

public class Rectangulo {
    private Coordenada superiorIzq, inferiorDer;

    public Rectangulo(Coordenada si, Coordenada id) {
        // Crear nuevas instancias de Coordenada en lugar de asignar directamente
        this.superiorIzq = new Coordenada(si.abcisa(), si.ordenada());
        this.inferiorDer = new Coordenada(id.abcisa(), id.ordenada());
    }

    public Coordenada superiorIzquierda() {
        return superiorIzq;
    }

    public Coordenada inferiorDerecha() {
        return inferiorDer;
    }

    @Override
    public String toString() {
        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";
    }
}
