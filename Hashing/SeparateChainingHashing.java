public class SeparateChainingHashing {

    int N;
    Nodo tabla[];

    public SeparateChainingHashing() {
        N = 10;
        tabla = new Nodo[N];
    }

    public static void main(String args[]) {

        SeparateChainingHashing sCH = new SeparateChainingHashing();
        // System.out.println(hash("Hola Mundo!", sCH.N));

        sCH.put("Polera", 13);

        sCH.imprimir();

        sCH.put("Falda", 1);

        sCH.imprimir();

        sCH.put("adla", 20);

        sCH.imprimir();

        sCH.put("Falda", 10);

        sCH.imprimir();

    }

    public void imprimir() {
        System.out.println("----");
        for (int i = 0; i < N; i++) {
            if (tabla[i] == null)
                System.out.println(i + ": null");
            else { // Imprimir la lista:
                Nodo aux = tabla[i]; // aux no es null
                while (aux != null) {
                    System.out.print(aux.llave + ": " + aux.valor + "  ");
                    aux = aux.sgte;
                }
                System.out.println();
            }
        }
        System.out.println("----");
    }

    public static int hash(String key, int n) {

        int suma = 0;
        for (int i = 0; i < key.length(); i++) {
            suma += key.charAt(i);
        }

        // Debemos evitar negativos y overflows
        return Math.abs(suma % n);
    }

    public void put(String llave, int valor) {
        int h = hash(llave, N);

        if (tabla[h] == null) {
            tabla[h] = new Nodo(llave, valor);
            return;
        } else {
            // Hay al menos un elemento en la lista
            Nodo aux = tabla[h];

            // aux no puede ser null
            while (aux != null) {
                if (aux.llave.equals(llave)) {
                    aux.valor = valor;
                    return;
                }
                if (aux.sgte == null) {
                    aux.sgte = new Nodo(llave, valor);
                    return;
                }
                aux = aux.sgte;
            }
            /*
             * // aqui aux.sgte = null
             * // Debemos agregar el nuevo Nodo
             * System.out.println("llave:" + llave);
             * aux.sgte = new Nodo(llave, valor);
             */
        }
    }
}
