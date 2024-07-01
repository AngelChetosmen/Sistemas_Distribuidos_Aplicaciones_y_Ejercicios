package C20;

public class Coordenada {
    private double x, y, magnitud;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
        this.magnitud = Math.sqrt(x * x + y * y); // Calcular la magnitud al origen
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

    public double getMagnitud() {
        return magnitud;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}