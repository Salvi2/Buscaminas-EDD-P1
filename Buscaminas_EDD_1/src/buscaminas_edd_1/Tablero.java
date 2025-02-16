package buscaminas_edd_1;
import winnPanel.WIN;
import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private ListaEnlazada minasColocadas;
    private boolean usarBFS = true;
    private int banderasDisponibles;

    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
            this.banderasDisponibles = numMinas; // Inicializar con el número de minas
        this.casillas = new Casilla[filas][columnas];
        this.minasColocadas = new ListaEnlazada();
        setLayout(new GridLayout(filas, columnas));
        crearTablero();
        construirGrafo();
        colocarMinas();
        contarMinasAdyacentes();
    }
    
    public void actualizarBanderasDisponibles(int cambio) {
    banderasDisponibles += cambio;
    
    
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
        verificarVictoria(); // Verificar si el jugador ha ganado
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
                if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
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
                if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
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
    
    public boolean todasLasMinasMarcadas() {
    Nodo actual = minasColocadas.getCabeza();
    while (actual != null) {
        if (!actual.casilla.estaMarcadaConBandera()) {
            return false; // Si alguna mina no está marcada, retornar false
        }
        actual = actual.siguiente;
    }
    return true; // Todas las minas están marcadas
    }
    
public void verificarVictoria() {
    // Verificar si todas las casillas no minas están reveladas
    boolean todasReveladas = true;
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            Casilla casilla = casillas[i][j];
            if (!casilla.esMina() && !casilla.estaRevelada()) {
                todasReveladas = false;
                break; // Salir del bucle si encontramos una casilla no revelada
            }
        }
        if (!todasReveladas) {
            break; // Salir del bucle exterior si ya sabemos que no todas están reveladas
        }
    }

    // Verificar si todas las minas están marcadas con banderas
    boolean todasMinasMarcadas = todasLasMinasMarcadas();

 

    // Si se cumplen ambas condiciones, abrir la ventana de victoria
    if (todasReveladas && todasMinasMarcadas) {
        WIN winPanel = new WIN();
        winPanel.setVisible(true);
        winPanel.setLocationRelativeTo(null);
            // Deshabilitar todas las casillas
        for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            casillas[i][j].setEnabled(false);
        }
        }
        
        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventanaPrincipal.dispose();
        
    }
}
   
    public void marcarCasillaConBandera(Casilla casilla) {
        if (!casilla.estaRevelada()) {
        if (casilla.estaMarcadaConBandera()) {
            // Si ya estaba marcada, la desmarcamos
            casilla.marcarConBandera();
            actualizarBanderasDisponibles(1); // Aumentar banderas disponibles
        } else if (banderasDisponibles > 0) {
            // Si no estaba marcada y hay banderas disponibles, la marcamos
            casilla.marcarConBandera();
            actualizarBanderasDisponibles(-1); // Reducir banderas disponibles
        }
        verificarVictoria(); // Verificar si el jugador ha ganado
    }
}
}