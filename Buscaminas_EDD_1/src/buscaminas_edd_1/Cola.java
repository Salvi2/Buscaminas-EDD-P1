package buscaminas_edd_1;

/**
 * Clase que representa una cola de casillas.
 * Utilizada para implementar el algoritmo BFS en el tablero del Buscaminas.
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;

    /**
     * Agrega una casilla a la cola.
     *
     * @param casilla Casilla a agregar.
     */
    public void encolar(Casilla casilla) {
        Nodo nuevo = new Nodo(casilla);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    /**
     * Elimina y devuelve la casilla al frente de la cola.
     *
     * @return Casilla al frente de la cola, o null si la cola está vacía.
     */
    public Casilla desencolar() {
        if (frente == null) return null;
        Casilla dato = frente.casilla;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return dato;
    }

    /**
     * Indica si la cola está vacía.
     *
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return frente == null;
    }
}