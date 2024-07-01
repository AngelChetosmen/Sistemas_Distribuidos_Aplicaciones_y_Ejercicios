package C14;

public class Coordenada {
    private double x, y;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setAbcisa(double x) {
        this.x = x;
    }

    public void setOrdenada(double y) {
        this.y = y;
    }

    public double abcisa() {
        return x;
    }

    public double ordenada() {
        return y;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
