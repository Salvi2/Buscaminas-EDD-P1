package buscaminas_edd_1;

public class ListaEnlazada {
    private Nodo cabeza;
    private Nodo cola;

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

    public boolean contiene(Casilla casilla) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.casilla == casilla) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}