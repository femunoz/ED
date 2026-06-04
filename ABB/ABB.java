import java.util.Stack;
import java.math.*;

public class ABB {
    Nodo raiz;

    public ABB() {
        raiz = null;
    }

    public void imprimir(Nodo nodoAImprimir) {
        if (nodoAImprimir != null) {
            imprimir(nodoAImprimir.izq);
            System.out.println(nodoAImprimir.valor);
            imprimir(nodoAImprimir.der);
        } else
            System.out.print(" ");

    }

    public static void main(String[] args) {
        ABB abb = new ABB();

        // Queremos insertar los valores: 7, 3, 20 y 2

        /*
         * abb.raiz = new Nodo(7);
         * 
         * abb.raiz.izq = new Nodo(3);
         * abb.raiz.der = new Nodo(20);
         * 
         * abb.raiz.izq.izq = new Nodo(2);
         * 
         */
        abb.raiz = abb.insert(7, abb.raiz);
        // abb.raiz = abb.insert(3, abb.raiz);
        abb.raiz = abb.insert(20, abb.raiz);
        // abb.raiz = abb.insert(2, abb.raiz);
        abb.raiz = abb.insert(17, abb.raiz);
        abb.raiz = abb.insert(33, abb.raiz);
        // abb.raiz = abb.insert(203, abb.raiz);
        abb.raiz = abb.insert(-2, abb.raiz);

        // abb.imprimir(abb.raiz);

        // System.out.println(abb.profundidad(abb.raiz));

        System.out.println(abb.buscar(20, abb.raiz).valor);

    }

    // VERSIÓN RECURSIVA:
    public Nodo insertar(int valor, Nodo enNodo) {

        if (enNodo == null)
            return new Nodo(valor);
        else if (valor < enNodo.valor)
            enNodo.izq = insertar(valor, enNodo.izq);
        else
            enNodo.der = insertar(valor, enNodo.der);

        return enNodo;
    }

    // VERSIÓN ITERATIVA: (similar al recorrido de listas)
    public Nodo insert(int valor, Nodo enNodo) {

        if (enNodo == null) {
            enNodo = new Nodo(valor);
            return enNodo;
        }

        Nodo actual = enNodo;
        while (true) {

            if (valor < actual.valor) {
                // por izq:
                if (actual.izq == null) {
                    actual.izq = new Nodo(valor);
                    return enNodo;
                } else
                    actual = actual.izq;

            } else {
                // por der:
                if (actual.der == null) {
                    actual.der = new Nodo(valor);
                    return enNodo;
                } else
                    actual = actual.der;
            }
        }
    }

    public Nodo buscar(int valor, Nodo enNodo) {

        if (enNodo == null) {
            return null;
        }

        Nodo actual = enNodo;
        while (true) {

            if (valor < actual.valor) {
                // por izq:
                if (actual.izq == null) {

                    return null;
                } else
                    actual = actual.izq;

            } else if (valor > actual.valor) {
                // por der:
                if (actual.der == null) {
                    return null;
                } else
                    actual = actual.der;
            } else {
                return actual;
            }
        }
    }

    public int profundidad(Nodo nodo) {

        if (nodo == null) {
            return -1;
        } else {
            return 1 + Math.max(profundidad(nodo.izq), profundidad(nodo.der));
        }

    }

}
