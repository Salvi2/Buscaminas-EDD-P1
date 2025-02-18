package buscaminas_edd_1;

public class Pila {
    private Nodo tope;

    public void apilar(Casilla casilla) {
        Nodo nuevo = new Nodo(casilla);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Casilla desapilar() {
        if (tope == null) return null;
        Casilla dato = tope.casilla;
        tope = tope.siguiente;
        return dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }
}