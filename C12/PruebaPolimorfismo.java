package C12;

public class PruebaPolimorfismo {
    public static void main(String[] args) {
        // Crear un arreglo de tres objetos Animal
        Animal[] animales = new Animal[3];

        // Inicializar cada elemento del arreglo con objetos Perro, Gato, Pajaro
        animales[0] = new Perro();
        animales[1] = new Gato();
        animales[2] = new Pajaro();

        // Llamar al método hacerRuido() para cada objeto del arreglo
        for (Animal animal : animales) {
            System.out.println(animal.hacerRuido());
        }
    }
}
