public class Nodo {
    String llave;
    int valor;
    Nodo sgte;

    public Nodo(String llave, int valor) {
        this.llave = llave;
        this.valor = valor;
        this.sgte = null;
    }

}
