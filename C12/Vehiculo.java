package C12;

public class Vehiculo {
    private String matricula;
    private String modelo;
    private int potenciaHP;

    public Vehiculo(String matricula, String modelo, int potenciaHP) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.potenciaHP = potenciaHP;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPotenciaHP() {
        return potenciaHP;
    }
}

class Taxi extends Vehiculo {
    private String numeroLicencia;

    public Taxi(String matricula, String modelo, int potenciaHP, String numeroLicencia) {
        super(matricula, modelo, potenciaHP);
        this.numeroLicencia = numeroLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public static void main(String[] args) {
        // Crear un objeto Taxi
        Taxi taxi = new Taxi("ABC123", "Toyota", 150, "Licencia123");

        // Acceder a los métodos comunes de Vehiculo desde Taxi
        System.out.println("Matricula del Taxi: " + taxi.getMatricula());
        System.out.println("Modelo del Taxi: " + taxi.getModelo());
        System.out.println("Potencia del Taxi: " + taxi.getPotenciaHP());

        // Acceder a los métodos específicos de Taxi
        System.out.println("Número de Licencia del Taxi: " + taxi.getNumeroLicencia());
    }
}
