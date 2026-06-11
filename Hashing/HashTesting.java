import java.io.*;
 
public class HashTesting {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String palabra;

        /*
         * while (true) {
         * System.out.println("Ingrese letra: ");
         * palabra = in.readLine();
         * System.out.println(charToInt(palabra.charAt(0)));
         * }
         */

        while (true) {
            System.out.println("Ingrese palabra: ");
            palabra = in.readLine();
            System.out.println(SeparateChainingHashing.hash(palabra, 10));
        }

    }

    public static int charToInt(char c) {
        return 0 + c;
    }
}
