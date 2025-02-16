package buscaminas_edd_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Agregar ActionListener para manejar clics
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClic(); // Llamar al método onClic cuando se hace clic
            }
        });
    }

    // Método para manejar clics
    public void onClic() {
        if (esMina) {
            setText("💣"); // Mostrar una mina
            setEnabled(false); // Deshabilitar la casilla

            // Mostrar mensaje de derrota y opciones
            int opcion = JOptionPane.showOptionDialog(
                null,
                "¡Perdiste! Has activado una mina. ¿Qué deseas hacer?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Reiniciar", "Salir" }, // Opciones personalizadas
                "Reiniciar" // Opción por defecto
            );

            // Manejar la opción seleccionada
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego(); // Reiniciar el juego
            } else {
                System.exit(0); // Salir del programa
            }
        } else {
            setText(String.valueOf(minasAdyacentes)); // Mostrar el número de minas adyacentes
            setEnabled(false); // Deshabilitar la casilla
        }
    }

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose(); // Cerrar la ventana actual

        // Crear una nueva ventana con un nuevo tablero
        new Buscaminas();
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