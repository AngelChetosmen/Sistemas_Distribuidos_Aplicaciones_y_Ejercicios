package A_Herencia;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cuauhtemoc_villalba
 */
public class Vehiculo {
    // Atributos
    private String matricula;
    private String modelo;
    private int potenciaHP;

    public Vehiculo(String matricula, String modelo, int potenciaHP) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.potenciaHP = potenciaHP;
    }

    // Getters
    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPotenciaHP() {
        return potenciaHP;
    }

    // Setters
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPotenciaHP(int potenciaHP) {
        this.potenciaHP = potenciaHP;
    }

    public void mostrarInformacion() {
        System.out.println("Matricula: " + this.getMatricula());
        System.out.println("Modelo: " + this.getModelo());
        System.out.println("Potencia: " + this.getPotenciaHP());
    }

    public static void main(String[] args) {
        Vehiculo auto = new Vehiculo("wd21", "ford", 123);
        auto.mostrarInformacion();

        Taxi taxi = new Taxi("ABC123", "Toyota", 150, "Licencia123");
        taxi.mostrarInfoTaxi();
        Autobus autobus = new Autobus("ABC123", "Toyota", 150, 12);
        autobus.mostrarInformacionAutobus();

    }

}

class Autobus extends Vehiculo {
    private int numeroPlazas;

    public Autobus(String matricula, String modelo, int potenciaHP, int numeroPlazas) {
        super(matricula, modelo, potenciaHP);
        this.numeroPlazas = numeroPlazas;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroDePlazas) {
        this.numeroPlazas = numeroDePlazas;
    }

    public void mostrarInformacionAutobus() {
        // Acceder a los métodos comunes de Vehiculo desde Taxi
        System.out.println("Matrícula del Autobus: " + this.getMatricula());
        System.out.println("Modelo del Autobus: " + this.getModelo());
        System.out.println("Potencia del Autobus: " + this.getPotenciaHP());

        // Acceder a los métodos específicos de Taxi
        System.out.println("Número de palzas: " + this.getNumeroPlazas());
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

    public void mostrarInfoTaxi() {
        // Acceder a los métodos comunes de Vehiculo desde Taxi
        System.out.println("Matrícula del Taxi: " + this.getMatricula());
        System.out.println("Modelo del Taxi: " + this.getModelo());
        System.out.println("Potencia del Taxi: " + this.getPotenciaHP());

        // Acceder a los métodos específicos de Taxi
        System.out.println("Número de Licencia del Taxi: " + this.getNumeroLicencia());
    }
}
