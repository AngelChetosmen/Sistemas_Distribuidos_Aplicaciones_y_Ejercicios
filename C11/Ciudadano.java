package C11;

import java.util.Random;
import java.util.Scanner;

public class Ciudadano {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private char sexo;
    private String entidadFederativa;
    private String fechaNacimiento;
    String curp = "";

    private static boolean esConsonante(char letra) {
        return "BCDFGHJKLMNPQRSTVWXYZ".indexOf(letra) != -1;
    }

    private static String obtenerConsonantesInternas(String cadena) {
        StringBuilder consonantesInternas = new StringBuilder();
        String cadenaMayusculas = cadena.toUpperCase();
        for (int i = 1; i < cadena.length(); i++) {
            char letra = cadenaMayusculas.charAt(i);
            if (esConsonante(letra)) {
                consonantesInternas.append(letra);
                break;
            }
        }
        return consonantesInternas.toString();
    }

    // Métodos getter y setter para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para el apellido paterno
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    } // Métodos getter y setter para el apellido paterno

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    // Métodos getter y setter para el sexo
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    // Métodos getter y setter para la entidad federativa
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    } // Métodos getter y setter para la fecha de nacimiento

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void obtieneCurp(Ciudadano ciudadano) {
        String[] ciudades = { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche",
                "Coahuila de Zaragoza", "Colima", "Chiapas", "Chihuahua",
                "Ciudad de México", "Durango", "Guanajuato", "Guerrero",
                "Hidalgo", "Jalisco", "México", "Michoacán de Ocampo",
                "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla",
                "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa",
                "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz de Ignacio de la Llave",
                "Yucatán", "Zacatecas"
        };
        String[] abreviaturas = { "AS", "BC", "BS", "CC",
                "CL", "CM", "CS", "CH",
                "DF", "DG", "GT", "GR",
                "HG", "JC", "MC", "MN",
                "MS", "NT", "NL", "OC",
                "PL", "QT", "QR", "SP",
                "SL", "SR", "TC", "TS",
                "TL", "VZ", "YN", "ZS"
        };
        Random random = new Random();
        int numero = random.nextInt(99); // Cambia el límite superior según tus necesidades
        // Concatenación inicial
        curp += ciudadano.getApellidoPaterno().substring(0, 2).toUpperCase();
        curp += ciudadano.getApellidoMaterno().substring(0, 1).toUpperCase();
        curp += ciudadano.getNombre().substring(0, 1).toUpperCase();
        curp += ciudadano.getFechaNacimiento();
        curp += ciudadano.getSexo();
        System.out.println("Selecciona una ciudad:");
        for (int i = 0; i < ciudades.length; i++) {
            System.out.println((i + 1) + ". " + ciudades[i]);
        }
        // Leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el número de la opción deseada:");
        int opcionSeleccionada = scanner.nextInt();
        // Validar la opción seleccionada
        if (opcionSeleccionada >= 1 && opcionSeleccionada <= ciudades.length) {
            // Obtener la ciudad y abreviatura correspondientes
            // String ciudadSeleccionada = ciudades[opcionSeleccionada - 1];
            String abreviatura = abreviaturas[opcionSeleccionada - 1];
            ciudadano.setEntidadFederativa(abreviatura);
            curp += ciudadano.getEntidadFederativa();
        }
        String consonantesInternas = obtenerConsonantesInternas(ciudadano.getApellidoPaterno()) +
                obtenerConsonantesInternas(ciudadano.getApellidoMaterno()) +
                obtenerConsonantesInternas(ciudadano.getNombre());
        curp += consonantesInternas;
        curp += numero;
        System.out.println(curp);
    }

    public static void main(String[] args) {
        // Crear un objeto Ciudadano
        Scanner scanner = new Scanner(System.in);
        Ciudadano ciudadano = new Ciudadano();
        // Usar los métodos setter para establecer los atributos del ciudadano
        System.out.println("Ingrese su nombre: ");
        ciudadano.setNombre(scanner.nextLine());
        System.out.println("Ingrese apellido paterno: ");
        ciudadano.setApellidoPaterno(scanner.nextLine());
        System.out.println("Ingrese apellido materno: ");
        ciudadano.setApellidoMaterno(scanner.nextLine());
        System.out.println("Ingrese su sexo: ");
        ciudadano.setSexo(scanner.nextLine().charAt(0));
        System.out.println("Ingrese su fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        String[] separadorFechaNacimiento = fechaNacimiento.trim().split("/");
        String dia = separadorFechaNacimiento[0];
        String mes = separadorFechaNacimiento[1];
        String year = separadorFechaNacimiento[2].substring(2);
        ciudadano.setFechaNacimiento(year + mes + dia);
        ciudadano.obtieneCurp(ciudadano);
        // Usar los métodos getter para obtener los atributos del ciudadano y mostrarlos
        /*
         * System.out.println("Nombre: " + ciudadano.getNombre());
         * System.out.println("Apellido: " + ciudadano.getApellidoPaterno());
         * System.out.println("Apellido: " + ciudadano.getApellidoMaterno());
         * System.out.println("Sexo: " + ciudadano.getSexo());
         * System.out.println("Entidad Federativa: " +
         * ciudadano.getEntidadFederativa());
         * System.out.println("Fecha de Nacimiento: " + ciudadano.getFechaNacimiento());
         */
    }
}