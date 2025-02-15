package buscaminas_edd_1;

import javax.swing.*;

public class Casilla extends JButton {
    private final String id; // Identificador único (e.g., "A1")
    private boolean esMina; // True si tiene una mina
    private int minasAdyacentes; // Número de minas adyacentes

    // Constructor
    public Casilla(String id) {
        this.id = id;
        this.esMina = false; // Por defecto, no tiene una mina
        this.minasAdyacentes = 0; // Por defecto, no tiene minas adyacentes
        setText(id); // Mostrar el identificador en el botón
    }

    // Getter para el identificador
    public String getId() {
        return id;
    }

    // Getter y setter para esMina
    public boolean esMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    // Getter y setter para minasAdyacentes
    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }
}