package buscaminas_edd_1;

import javax.swing.*;
import java.awt.BorderLayout;

public class Buscaminas extends JFrame {
    private Tablero tablero;

    public Buscaminas() {
        setTitle("Buscaminas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Pedir al usuario las dimensiones del tablero
        int filas = Integer.parseInt(JOptionPane.showInputDialog("Número de filas (3-10):"));
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Número de columnas (3-10):"));
        int minas = Integer.parseInt(JOptionPane.showInputDialog("Número de minas:"));

        // Validar que las dimensiones estén dentro del rango permitido
        if (filas < 3 || filas > 10 || columnas < 3 || columnas > 10) {
            JOptionPane.showMessageDialog(null, "Las filas y columnas deben estar entre 3 y 10.");
            return;
        }

        // Validar que el número de minas no exceda el número de casillas
        if (minas > filas * columnas) {
            JOptionPane.showMessageDialog(null, "El número de minas no puede ser mayor que el número de casillas.");
            return;
        }

        // Crear el tablero
        tablero = new Tablero(filas, columnas, minas);

        // Añadir el tablero a la ventana en la posición CENTER
        add(tablero, BorderLayout.CENTER);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new Buscaminas());
    }
}