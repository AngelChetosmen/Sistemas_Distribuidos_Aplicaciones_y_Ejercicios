package C38;

import java.io.*;
import java.util.Arrays;

class Demo implements java.io.Serializable {
    public int a;
    public String b;

    // Default constructor
    public Demo(int a, String b) {
        this.a = a;
        this.b = b;
    }
}

class Test {
    public static void main(String[] args) {
        Demo object = new Demo(2024, "Prueba serializacion y deserializacion");
        Demo objeto = null;

        byte[] serializado = SerializationUtils.serialize(object);
        System.out.println("Objeto serializado byte por byte (-128 a 127):");
        System.out.println(Arrays.toString(serializado));
        objeto = (Demo) SerializationUtils.deserialize(serializado);
        System.out.println("a = " + objeto.a);
        System.out.println("b = " + objeto.b);
    }
}