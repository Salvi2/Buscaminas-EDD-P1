package buscaminas_edd_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Casilla extends JButton {
    private final String id;
    private boolean esMina;
    private int minasAdyacentes;
    private boolean revelada;
    private ListaEnlazada vecinos;

    public Casilla(String id) {
        this.id = id;
        this.esMina = false;
        this.minasAdyacentes = 0;
        this.revelada = false;
        this.vecinos = new ListaEnlazada();
        setText(id);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClic();
            }
        });
    }

    public void onClic() {
        if (esMina) {
            setText("💣");
            setEnabled(false);
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "¡Perdiste! ¿Reiniciar?", 
                "Game Over", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                new Object[]{"Reiniciar", "Salir"}, 
                "Reiniciar"
            );
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                System.exit(0);
            }
        } else {
            Tablero tablero = (Tablero) SwingUtilities.getAncestorOfClass(Tablero.class, this);
            if (tablero != null) {
                tablero.revelarDesde(this);
            }
        }
    }

    private void reiniciarJuego() {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose();
        new Buscaminas();
    }

    public void agregarVecino(Casilla vecino) {
        vecinos.agregar(vecino);
    }

    public ListaEnlazada getVecinos() {
        return vecinos;
    }

    public void revelar() {
        if (esMina) {
            setText("💣");
        } else {
            setText(minasAdyacentes > 0 ? String.valueOf(minasAdyacentes) : "");
        }
        setEnabled(false);
        revelada = true;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public String getId() {
        return id;
    }

    public boolean esMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }
}