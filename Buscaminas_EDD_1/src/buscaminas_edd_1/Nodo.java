package buscaminas_edd_1;

/**
 * Clase que representa un nodo en una lista enlazada.
 * Cada nodo contiene una referencia a una casilla y al siguiente nodo en la lista.
 */
class Nodo {
    Casilla casilla; // Referencia a la casilla
    Nodo siguiente;  // Referencia al siguiente nodo

    /**
     * Constructor del nodo.
     *
     * @param casilla Casilla asociada al nodo.
     */
    public Nodo(Casilla casilla) {
        this.casilla = casilla;
        this.siguiente = null;
    }
}