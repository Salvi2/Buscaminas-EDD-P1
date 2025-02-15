package buscaminas_edd_1;

class ListaEnlazada {
    private Nodo cabeza; // Referencia al primer nodo de la lista

    // Constructor
    public ListaEnlazada() {
        this.cabeza = null;
    }

    // Añadir una casilla a la lista
    public void agregar(Casilla casilla) {
        Nodo nuevoNodo = new Nodo(casilla);
        if (cabeza == null) {
            cabeza = nuevoNodo; // Si la lista está vacía, el nuevo nodo es la cabeza
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente; // Recorrer la lista hasta el último nodo
            }
            actual.siguiente = nuevoNodo; // Añadir el nuevo nodo al final
        }
    }

    // Verificar si una casilla ya está en la lista
    public boolean contiene(Casilla casilla) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.casilla == casilla) {
                return true; // La casilla ya está en la lista
            }
            actual = actual.siguiente;
        }
        return false; // La casilla no está en la lista
    }
}