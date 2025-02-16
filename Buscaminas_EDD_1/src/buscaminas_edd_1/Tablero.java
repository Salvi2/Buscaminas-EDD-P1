package buscaminas_edd_1;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private ListaEnlazada minasColocadas;
    private boolean usarBFS = true;

    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.casillas = new Casilla[filas][columnas];
        this.minasColocadas = new ListaEnlazada();
        setLayout(new GridLayout(filas, columnas));
        crearTablero();
        construirGrafo();
        colocarMinas();
        contarMinasAdyacentes();
    }

    private void crearTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String id = String.valueOf((char) ('A' + j)) + (i + 1);
                casillas[i][j] = new Casilla(id);
                add(casillas[i][j]);
            }
        }
    }

    private void construirGrafo() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla actual = casillas[i][j];
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < filas && y >= 0 && y < columnas && !(x == i && y == j)) {
                            actual.agregarVecino(casillas[x][y]);
                        }
                    }
                }
            }
        }
    }

    private void colocarMinas() {
        int minasColocadas = 0;
        while (minasColocadas < numMinas) {
            int fila = (int) (Math.random() * filas);
            int columna = (int) (Math.random() * columnas);
            Casilla casilla = casillas[fila][columna];
            if (!this.minasColocadas.contiene(casilla)) {
                casilla.setEsMina(true);
                this.minasColocadas.agregar(casilla);
                minasColocadas++;
            }
        }
    }

    private void contarMinasAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                if (!casilla.esMina()) {
                    int contador = 0;
                    Nodo vecino = casilla.getVecinos().getCabeza();
                    while (vecino != null) {
                        if (vecino.casilla.esMina()) contador++;
                        vecino = vecino.siguiente;
                    }
                    casilla.setMinasAdyacentes(contador);
                }
            }
        }
    }

    public void revelarDesde(Casilla inicio) {
        if (usarBFS) bfs(inicio);
        else dfs(inicio);
    }

    private void bfs(Casilla inicio) {
        Cola cola = new Cola();
        cola.encolar(inicio);
        inicio.revelar();

        while (!cola.estaVacia()) {
            Casilla actual = cola.desencolar();
            if (actual.getMinasAdyacentes() == 0) {
                Nodo vecino = actual.getVecinos().getCabeza();
                while (vecino != null) {
                    Casilla v = vecino.casilla;
                    if (!v.estaRevelada() && !v.esMina()) {
                        v.revelar();
                        cola.encolar(v);
                    }
                    vecino = vecino.siguiente;
                }
            }
        }
    }

    private void dfs(Casilla inicio) {
        Pila pila = new Pila();
        pila.apilar(inicio);
        inicio.revelar();

        while (!pila.estaVacia()) {
            Casilla actual = pila.desapilar();
            if (actual.getMinasAdyacentes() == 0) {
                Nodo vecino = actual.getVecinos().getCabeza();
                while (vecino != null) {
                    Casilla v = vecino.casilla;
                    if (!v.estaRevelada() && !v.esMina()) {
                        v.revelar();
                        pila.apilar(v);
                    }
                    vecino = vecino.siguiente;
                }
            }
        }
    }

    public void setUsarBFS(boolean usarBFS) {
        this.usarBFS = usarBFS;
    }
}