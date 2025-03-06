package buscaminas_edd_1;

class Nodo{
    Casilla casilla; // Referencia a la casilla
    Nodo siguiente;  // Referencia al siguiente nodo

    // Constructor
    public Nodo(Casilla casilla) {
        this.casilla = casilla;
        this.siguiente = null;
    }
}