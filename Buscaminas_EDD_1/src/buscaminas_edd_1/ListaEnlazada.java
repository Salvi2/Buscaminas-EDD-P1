package buscaminas_edd_1;

/**
 * Clase que representa una lista enlazada de casillas.
 * Utilizada para almacenar vecinos de una casilla y minas colocadas en el tablero.
 */
public class ListaEnlazada {
    private Nodo cabeza;
    private Nodo cola;

    /**
     * Agrega una casilla a la lista enlazada.
     *
     * @param casilla Casilla a agregar.
     */
    public void agregar(Casilla casilla) {
        Nodo nuevo = new Nodo(casilla);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    /**
     * Indica si la lista enlazada contiene una casilla específica.
     *
     * @param casilla Casilla a buscar.
     * @return true si la casilla está en la lista, false en caso contrario.
     */
    public boolean contiene(Casilla casilla) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.casilla == casilla) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Obtiene el primer nodo de la lista enlazada.
     *
     * @return Primer nodo de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }
}