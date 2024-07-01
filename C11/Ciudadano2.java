package C11;

import java.util.Scanner;

public class Ciudadano2 {
    private String nombre;
    private String fechaNacimiento;
    private char sexo;
    private String ciudad;

    // Constructor
    public Ciudadano2(String nombre, String fechaNacimiento, char sexo, String ciudad) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.ciudad = ciudad;
    }

    // Setters y Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // Método para obtener CURP
    public String obtieneCurp() {
        // Obtener iniciales de apellidos y nombres
        String iniciales = obtenerIniciales();

        // Obtener fecha de nacimiento
        String fechaNacimientoParte = obtenerFechaNacimiento();

        // Obtener sexo
        String sexoParte = String.valueOf(sexo);

        // Obtener entidad federativa
        String entidadParte = obtenerEntidadFederativa();

        // Obtener palabras aleatorias del nombre en mayúsculas
        String palabrasAleatorias = obtenerPalabrasAleatorias().toUpperCase();

        // Obtener número aleatorio
        String numeroAleatorio = generaNumerosAleatorios();

        // Formar CURP
        return iniciales + fechaNacimientoParte + sexoParte + entidadParte + palabrasAleatorias + numeroAleatorio;
    }

    // Método privado para obtener palabras aleatorias del nombre
    private String obtenerPalabrasAleatorias() {
        String[] partesNombre = nombre.split(" ");
        StringBuilder palabrasAleatorias = new StringBuilder();

        for (String parte : partesNombre) {
            if (!parte.isEmpty()) {
                char inicial = parte.charAt(0);
                if (esLetraMayuscula(inicial) && palabrasAleatorias.length() < 3
                        && palabrasAleatorias.indexOf(String.valueOf(inicial)) == -1) {
                    palabrasAleatorias.append(inicial);
                }
            }
        }
        return palabrasAleatorias.toString();
    }

    private boolean esLetraMayuscula(char letra) {
        return letra >= 'A' && letra <= 'Z';
    }

    // Método privado para generar letras aleatorias
    private char generarLetraAleatoria() {
        // Generar una letra aleatoria entre 'A' y 'Z'
        return (char) ('A' + (int) (Math.random() * 26));
    }

    // Método privado para obtener fecha de nacimiento
    private String obtenerFechaNacimiento() {
        String[] partesFecha = fechaNacimiento.split("/");

        if (partesFecha.length != 3) {
            System.out.println("Formato de fecha incorrecto. Por favor, use el formato AA/MM/DD.");
            System.exit(1);
        }

        // Asegurarse de que cada parte de la fecha tenga dos dígitos
        String año = String.format("%02d", Integer.parseInt(partesFecha[0]));
        String mes = String.format("%02d", Integer.parseInt(partesFecha[1]));
        String dia = String.format("%02d", Integer.parseInt(partesFecha[2]));

        return año + mes + dia;
    }

    // Método privado para obtener entidad federativa
    private String obtenerEntidadFederativa() {
        // Suponiendo que la ciudad es una clave de entidad de dos letras
        return ciudad;
    }

    // Método privado para obtener iniciales de todo el nombre
    private String obtenerInicialesNombre() {
        String[] partesNombre = nombre.split(" ");
        StringBuilder iniciales = new StringBuilder();

        for (String parte : partesNombre) {
            if (!parte.isEmpty()) {
                iniciales.append(parte.charAt(0));
            }
        }

        return iniciales.toString();
    }

    // Método privado para generar números aleatorios
    private String generaNumerosAleatorios() {
        // Generar dos dígitos aleatorios
        int numAleatorio = (int) (Math.random() * 100);
        return String.format("%02d", numAleatorio);
    }

    // Método privado para obtener iniciales
    private String obtenerIniciales() {
        String[] partesNombre = nombre.split(" ");
        String[] partesApellido = nombre.split(" ");

        String inicialApellido1 = obtenerInicial(partesApellido[0]);
        String inicialApellido2 = obtenerInicial(partesApellido[1]);
        String inicialNombre = obtenerInicial(partesNombre[2]);

        return inicialApellido1 + inicialApellido2 + inicialNombre;
    }

    // return inicialApellido1 + inicialApellido11 + inicialApellido2 +
    // inicialNombre;
    // Método privado para obtener la inicial de una palabra
    private String obtenerInicial(String palabra) {
        return palabra.substring(0, 1).toUpperCase();
    }

    // Método privado para obtener números aleatorios
    private String obtenerNumerosAleatorios() {
        // Generar aleatoriamente el año, mes y día
        String[] partesFecha = fechaNacimiento.split("/");
        String año = partesFecha[0].substring(2);
        String mes = partesFecha[1];
        String dia = partesFecha[2];
        return año + mes + dia;
    }

    // Método privado para obtener primeras consonantes internas
    private String obtenerConsonantesInternas() {
        String[] partesNombre = nombre.split(" ");
        String consonantes = "";

        for (String parte : partesNombre) {
            for (int i = 1; i < parte.length(); i++) {
                char letra = parte.charAt(i);
                if (!esVocal(letra)) {
                    consonantes += letra;
                    break;
                }
            }
        }
        return consonantes.toUpperCase();
    }

    // Método privado para verificar si una letra es vocal
    private boolean esVocal(char letra) {
        return "AEIOUaeiou".indexOf(letra) != -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Solicitar datos al usuario
            System.out.println("Ingrese su nombre completo (Comenzando por apellidos y nombre(s) ):");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la fecha de nacimiento (AA/MM/DD):");
            String fechaNacimiento = scanner.nextLine();

            System.out.println("Ingrese el sexo (H -> Hombre / M -> Mujer):");
            char sexo = scanner.nextLine().toUpperCase().charAt(0);

            System.out.println("Ingrese la entidad federativa (clave):");
            String ciudad = scanner.nextLine();

            // Crear instancia de Ciudadano
            Ciudadano2 ciudadano = new Ciudadano2(nombre, fechaNacimiento, sexo, ciudad);

            // Imprimir la CURP correspondiente
            System.out.println("La CURP del ciudadano es: " + ciudadano.obtieneCurp());

            // Preguntar al usuario si desea ingresar otro ciudadano
            System.out.println("¿Desea ingresar otro ciudadano? (S/N):");
            String respuesta = scanner.nextLine().toUpperCase();
            if (!respuesta.equals("S")) {
                break;
            }
        }

        // Cerrar el scanner al salir
        scanner.close();
    }
}