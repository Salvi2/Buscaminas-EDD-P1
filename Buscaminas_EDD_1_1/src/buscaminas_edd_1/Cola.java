package buscaminas_edd_1;

public class Cola {
    private Nodo frente;
    private Nodo fin;

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

    public Casilla desencolar() {
        if (frente == null) return null;
        Casilla dato = frente.casilla;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}