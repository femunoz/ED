import java.util.*;

class GuiaArboles {

    public static void main(String args[]) {
        ArrayList<Integer> desordenado = new ArrayList<Integer>();
        ArrayList<Integer> ordenado = new ArrayList<Integer>();

        // String s = new String("Hola");
        // s es un objeto de tipo/clase String
        // tipos basicos: int, float, char, boolean, ...

        Random aleatorio = new Random();

        int N = 10100000;

        // 1. Entero aleatorio entre 0 (incluido) y N (excluido)
        // int nroR = aleatorio.nextInt(N);

        // Iterable: interfaz que puede ser recorrida
        // con un ciclo.

        /*
         * for (int nro : desordenado) {
         * System.out.println(nro);
         * }
         */
        /*
         * System.out.println("CARGANDO ordenado...");
         */
        // int N = 101000000;

        for (int i = 0; i < N; i++) {

            int nroR = aleatorio.nextInt(N);
            if (nroR <= 1000)
                System.out.println(nroR);

            desordenado.add(nroR);
        }

        // int N = 10;

        for (int i = 0; i < N; i++) {
            ordenado.add(i);
            // System.out.println(ordenado.get(i));
        }

        System.out.println("FIN Carga...");

        long t1 = System.currentTimeMillis();

        System.out.println("Ininciando Buscar");
        System.out.println(buscar(ordenado, N - 1));

        long t2 = System.currentTimeMillis();

        System.out.println("Iniciando BBin");
        System.out.println(bbin(ordenado, 1));

        long t3 = System.currentTimeMillis();

        System.out.println("buscar: " + (t2 - t1));
        System.out.println("bbin: " + (t3 - t2));

    }

    // BUSQUEDA SECUENCIAL
    public static int buscar(ArrayList<Integer> ord, int busc) {

        for (int i = 0; i < ord.size(); i++) {
            // System.out.println((i + 1) + " buscar...");
            if (busc == ord.get(i))
                return i;
        }
        return -1;
    }

    // BUSQUEDA BINARIA: (arreglo ordenado)

    public static int bbin(ArrayList<Integer> ord, int busc) {
        int i = 0;
        int j = ord.size() - 1;

        int cont = 0;

        while (i <= j) {
            int m = (i + j) / 2;
            // System.out.println((cont + 1) + " bbin...");
            cont++;

            if (busc == ord.get(m))
                return m;
            else if (busc < ord.get(m))
                j = m - 1;
            else
                i = m + 1;
        }
        return -1;
    }

    /*
     * public static int bbin(ArrayList<Integer> ord, int busc) {
     * 
     * int i = 0;
     * int j = ord.size() - 1;
     * 
     * while (i <= j) {
     * System.out.println("bbin...");
     * 
     * int m = (i + j) / 2;
     * 
     * int ord_m = ord.get(m);
     * 
     * if (busc == ord_m) {
     * return m;
     * } else if (busc < ord_m) {
     * j = m - 1;
     * } else {
     * i = m + 1;
     * }
     * }
     */

}