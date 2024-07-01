package C12;

class Animal {
    public String hacerRuido() {
        return "ruido desconocido";
    }
}

class Perro extends Animal {
    @Override
    public String hacerRuido() {
        return "ladrar";
    }
}

class Gato extends Animal {
    @Override
    public String hacerRuido() {
        return "miau";
    }
}

class Pajaro extends Animal {
    @Override
    public String hacerRuido() {
        return "pio";
    }
}
/*
 * class PruebaPolimorfismo {
 * public static void main(String[] args) {
 * // Crear un arreglo de tres objetos Animal
 * Animal[] animales = new Animal[3];
 * 
 * // Inicializar cada elemento del arreglo con objetos Perro, Gato, Pajaro
 * animales[0] = new Perro();
 * animales[1] = new Gato();
 * animales[2] = new Pajaro();
 * 
 * // Llamar al método hacerRuido() para cada objeto del arreglo
 * for (Animal animal : animales) {
 * System.out.println(animal.hacerRuido());
 * }
 * }
 * }
 */