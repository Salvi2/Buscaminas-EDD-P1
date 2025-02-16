package buscaminas_edd_1;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel {
    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private ListaEnlazada minasColocadas; // Lista enlazada para almacenar minas

    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.casillas = new Casilla[filas][columnas];
        this.minasColocadas = new ListaEnlazada(); // Inicializar la lista enlazada
        setLayout(new GridLayout(filas, columnas)); // Usar GridLayout para el tablero
        crearTablero();
        colocarMinas();
        contarMinasAdyacentes(); // Calcular minas adyacentes para cada casilla
    }

    // Crear el tablero y asignar identificadores
    private void crearTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Asignar identificador (e.g., "A1", "B2")
                String id = String.valueOf((char) ('A' + j)) + (i + 1);
                casillas[i][j] = new Casilla(id);
                add(casillas[i][j]); // Añadir la casilla al panel
            }
        }
    }

    // Colocar minas aleatoriamente
    private void colocarMinas() {
        int minasColocadas = 0;

        while (minasColocadas < numMinas) {
            // Generar fila y columna aleatorias
            int fila = (int) (Math.random() * filas);
            int columna = (int) (Math.random() * columnas);

            // Obtener la casilla en la posición generada
            Casilla casilla = casillas[fila][columna];

            // Verificar si la casilla ya tiene una mina
            if (!this.minasColocadas.contiene(casilla)) {
                casilla.setEsMina(true);
                this.minasColocadas.agregar(casilla); // Añadir a la lista de minas
                minasColocadas++;
            }
        }
    }

    // Contar minas adyacentes para cada casilla
    private void contarMinasAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                if (!casilla.esMina()) {
                    int minasAdyacentes = 0;

                    // Verificar las 8 casillas adyacentes
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x >= 0 && x < filas && y >= 0 && y < columnas && casillas[x][y].esMina()) {
                                minasAdyacentes++;
                            }
                        }
                    }

                    casilla.setMinasAdyacentes(minasAdyacentes); // Asignar el número de minas adyacentes
                }
            }
        }
    }
}