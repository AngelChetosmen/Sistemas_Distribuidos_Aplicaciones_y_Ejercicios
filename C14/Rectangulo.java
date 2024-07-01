package C14;

public class Rectangulo extends Figura implements Perimetro {
    private Coordenada superiorIzq, inferiorDer;

    public Rectangulo(Coordenada si, Coordenada id) {
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
    public float area() {
        double base = Math.abs(superiorIzq.abcisa() - inferiorDer.abcisa());
        double altura = Math.abs(superiorIzq.ordenada() - inferiorDer.ordenada());
        return (float) (base * altura);
    }

    @Override
    public float imprimePerimetro() {
        double base = Math.abs(superiorIzq.abcisa() - inferiorDer.abcisa());
        double altura = Math.abs(superiorIzq.ordenada() - inferiorDer.ordenada());
        return (float) (2 * (base + altura));
    }

    @Override
    public String toString() {
        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";
    }
}
