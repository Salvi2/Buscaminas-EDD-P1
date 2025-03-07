package buscaminas_edd_1;

/**
 * Clase que representa una pila de casillas.
 * Utilizada para implementar el algoritmo DFS en el tablero del Buscaminas.
 */
public class Pila {
    private Nodo tope;

    /**
     * Agrega una casilla a la pila.
     *
     * @param casilla Casilla a agregar.
     */
    public void apilar(Casilla casilla) {
        Nodo nuevo = new Nodo(casilla);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    /**
     * Elimina y devuelve la casilla en el tope de la pila.
     *
     * @return Casilla en el tope de la pila, o null si la pila está vacía.
     */
    public Casilla desapilar() {
        if (tope == null) return null;
        Casilla dato = tope.casilla;
        tope = tope.siguiente;
        return dato;
    }

    /**
     * Indica si la pila está vacía.
     *
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return tope == null;
    }
}