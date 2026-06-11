## Enunciado del Problema: "El Registro de Inventario Seguro"

Estás diseñando el sistema de inventario para una tienda en línea. Cada producto tiene una clave única (un código de barras de tipo `String`) y un valor asociado (el stock disponible, un `Integer`).

Debido a que el volumen de productos es masivo, debes implementar tu propia Tabla Hash de tamaño fijo $m = 10$. Para gestionar las colisiones de manera eficiente y evitar la pérdida de datos, debes utilizar la estrategia de **Encadenamiento (Separate Chaining)** mediante listas enlazadas hechas a mano (sin usar las colecciones nativas de Java como `LinkedList`).

### Requisitos:

1. Implementar una función hash, `private int hash(String key, int m)`, basada en la suma de los valores ASCII de los caracteres del `String`, asegurando que el índice final esté en el rango $[0, m-1]$ (manejando correctamente posibles hashes negativos).
2. Implementar el método `put(String key, int value)`: Si la clave ya existe, actualiza su stock. Si no existe, inserta el nuevo nodo al **inicio** de la lista enlazada correspondiente (inserción $O(1)$).
3. Implementar el método `get(String key)`: Devuelve el stock del producto. Si no existe, devuelve `-1`.
4. Implementar un método `printTable()` que muestre visualmente el estado de la tabla y cómo se forman las cadenas.

---

## Concepto: Encadenamiento con Listas Enlazadas

A diferencia del _Linear Probing_ (donde buscas el siguiente espacio vacío en el arreglo), en el **Separate Chaining** cada posición del arreglo (`bucket`) apunta a una lista enlazada. Si múltiples claves generan el mismo índice, simplemente se "encadenan" en esa posición.

---

## Solución en Java

Aquí tienes la arquitectura completa del programa:

```java
public class HashTableChaining {

    // 1. Nodo de la Lista Enlazada
    private static class Node {
        String key;
        int value;
        Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // Atributos de la Tabla Hash
    private final int tableSize;
    private final Node[] table;

    // Constructor
    public HashTableChaining(int size) {
        this.tableSize = size;
        this.table = new Node[size]; // Inicializa el arreglo con nulls
    }

    // 2. Función Hash (Suma ASCII + Máscara para evitar negativos)
    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        // Aplicamos máscara de bits para asegurar que sea positivo antes del módulo
        return (hashValue & 0x7FFFFFFF) % tableSize;
    }

    // 3. Método Put (Insertar o Actualizar)
    public void put(String key, int value) {
        int index = hash(key);
        Node current = table[index];

        // Buscar si la clave ya existe en la lista para actualizarla
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Actualización
                return;
            }
            current = current.next;
        }

        // Si no existe, insertamos un nuevo nodo al INICIO de la lista (O(1))
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
    }

    // 4. Método Get (Buscar)
    public int get(String key) {
        int index = hash(key);
        Node current = table[index];

        // Recorrer la lista enlazada en esa posición
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Clave encontrada
            }
            current = current.next;
        }
        return -1; // Clave no encontrada
    }

    // 5. Método para visualizar la estructura
    public void printTable() {
        System.out.println("\n--- ESTADO DE LA TABLA HASH ---");
        for (int i = 0; i < tableSize; i++) {
            System.out.print("Bucket [" + i + "]: ");
            Node current = table[i];
            if (current == null) {
                System.out.print("null");
            } else {
                while (current != null) {
                    System.out.print("(" + current.key + " => Stock: " + current.value + ") -> ");
                    current = current.next;
                }
                System.out.print("null");
            }
            System.out.println();
        }
    }

    // Método Main para probar el comportamiento y provocar colisiones
    public static void main(String[] args) {
        HashTableChaining inventario = new HashTableChaining(10);

        // Insertamos productos
        inventario.put("Laptop", 15);
        inventario.put("Mouse", 50);
        inventario.put("Teclado", 30);

        // "Monitor" y "Teclado" podrían colisionar dependiendo del valor ASCII sumado
        inventario.put("Monitor", 8);
        inventario.put("Phone", 22);
        inventario.put("Pad", 100);

        // Actualizamos un valor existente
        inventario.put("Mouse", 45);

        // Imprimimos la tabla para ver las colisiones y las cadenas
        inventario.printTable();

        // Demostración de búsquedas
        System.out.println("\n--- PRUEBAS DE BÚSQUEDA ---");
        System.out.println("Stock de 'Laptop': " + inventario.get("Laptop"));  // Debería ser 15
        System.out.println("Stock de 'Mouse': " + inventario.get("Mouse"));    // Debería ser 45 (actualizado)
        System.out.println("Stock de 'Impresora': " + inventario.get("Impresora")); // Debería ser -1
    }
}

```

---

## Análisis de esta solución

1. **Evitar desbordamientos negativos:** Al igual que discutimos antes, se utiliza `(hashValue & 0x7FFFFFFF)` para mitigar cualquier riesgo de índice negativo antes de aplicar `% tableSize`.
2. **Eficiencia en Colisiones:** Al insertar al inicio (`newNode.next = table[index]`), la inserción toma tiempo constante $O(1)$, sin importar qué tan larga sea la lista en ese momento.
3. **Peor caso (Worst Case):** Si la función hash fuera muy mala y enviara todas las claves al mismo índice, la tabla hash se degradaría en una simple Lista Enlazada, transformando las búsquedas `get()` de $O(1)$ promedio a $O(n)$.
