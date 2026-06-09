
class LinealProbingHashing {

    int N = 10;
    Integer a[];

    public LinealProbingHashing() {
        a = new Integer[N];
    }

    static public void main(String args[]) throws Exception {
        LinealProbingHashing hash = new LinealProbingHashing();

        hash.insertar(1); // 1
        hash.insertar(11); // 2
        hash.insertar(21); // 3
        hash.insertar(9); // 4
        hash.insertar(19); // 5
        hash.insertar(7); // 6
        hash.insertar(17); // 7
        hash.insertar(27); // 8
        hash.insertar(37); // 9
        hash.insertar(57); // 10
        // hash.insertar(67); // 11

        System.out.println(hash.buscar(67));

        for (Integer nro : hash.a) {
            System.out.print(nro + " ");
        }

    }

    public int h(Integer x, int n) {
        return x % n;
    }

    public void insertar(int valor) throws Exception {
        int hash = h(valor, N);

        if (a[hash] == null) {
            if (!isFull()) {
                a[hash] = valor;
            } else {
                throw new Exception();
            }
        } else {
            if (a[hash] == null) { // Hay al menos un espacio libre
                a[hash] = valor;
            } else {
                if (isFull())
                    throw new Exception("¡Tabla llena!");
                while (a[hash] != null) {
                    hash = (hash + 1) % N;
                }

                a[hash % N] = valor;
            }
        }
    }

    public Integer buscar(int valor) {
        int hash = h(valor, N);

        if (a[hash] == valor)
            return valor;
        else {

            hash = (hash + 1) % N;
            while (a[hash] != null) {
                if (a[hash] == valor)
                    return valor;
                hash = (hash + 1) % N;
                if (isFull())
                    return null;
            }
            // FIN WHILE: a[hash] = null, no encontramos el valor
            return null;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < N; i++) {
            if (a[i] == null)
                return false;
        }
        return true;
    }

}
