package buscaminas_edd_1;
import losePanel.Lose;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que representa una casilla en el tablero del Buscaminas.
 * Cada casilla puede contener una mina, estar revelada, marcada con una bandera, y tener un número de minas adyacentes.
 */
public class Casilla extends JButton {
    private final String id;
    private boolean esMina;
    private int minasAdyacentes;
    private boolean revelada;
    private ListaEnlazada vecinos;
    private boolean marcadaConBandera;

    /**
     * Constructor de la casilla.
     *
     * @param id Identificador único de la casilla.
     */
    public Casilla(String id) {
        this.id = id;
        this.esMina = false;
        this.minasAdyacentes = 0;
        this.revelada = false;
        this.marcadaConBandera = false;
        this.vecinos = new ListaEnlazada();
        setText(id);

        // Manejar clic izquierdo
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClic();
            }
        });

        // Manejar clic derecho
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Tablero tablero = (Tablero) SwingUtilities.getAncestorOfClass(Tablero.class, Casilla.this);
                    if (tablero != null) {
                        tablero.marcarCasillaConBandera(Casilla.this);
                    }
                }
            }
        });
    }

    /**
     * Constructor no implementado.
     *
     * @param id Identificador de la casilla.
     * @param esMina Indica si la casilla es una mina.
     * @param revelada Indica si la casilla está revelada.
     * @param bandera Indica si la casilla está marcada con una bandera.
     * @param minasAdyacentes Número de minas adyacentes.
     */
    public Casilla(int id, boolean esMina, boolean revelada, boolean bandera, int minasAdyacentes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Constructor no implementado.
     */
    public Casilla() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Constructor no implementado.
     *
     * @param id Identificador de la casilla.
     * @param esMina Indica si la casilla es una mina.
     * @param revelada Indica si la casilla está revelada.
     * @param bandera Indica si la casilla está marcada con una bandera.
     * @param minasAdyacentes Número de minas adyacentes.
     */
    public Casilla(String id, boolean esMina, boolean revelada, boolean bandera, int minasAdyacentes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método que se ejecuta al hacer clic en la casilla.
     * Si la casilla es una mina, se muestra la explosión y se termina el juego.
     * Si no es una mina, se revela la casilla y se propaga la revelación a las casillas adyacentes.
     */
    public void onClic() {
        if (marcadaConBandera) {
            return;
        }
        if (esMina) {
            setText("💣");
            setEnabled(false);

            Lose loseFrame = new Lose();
            loseFrame.setVisible(true);
            loseFrame.setLocationRelativeTo(null);

            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.dispose();
        } else {
            Tablero tablero = (Tablero) SwingUtilities.getAncestorOfClass(Tablero.class, this);
            if (tablero != null) {
                tablero.revelarDesde(this);
            }
        }
    }

    /**
     * Marca o desmarca la casilla con una bandera.
     * Solo se puede marcar si la casilla no está revelada.
     */
    public void marcarConBandera() {
        if (!revelada) {
            marcadaConBandera = !marcadaConBandera;
            setText(marcadaConBandera ? "🚩" : id);
        }
    }

    /**
     * Indica si la casilla está marcada con una bandera.
     *
     * @return true si la casilla está marcada con una bandera, false en caso contrario.
     */
    public boolean estaMarcadaConBandera() {
        return marcadaConBandera;
    }

    /**
     * Reinicia el juego cerrando la ventana actual y abriendo una nueva.
     */
    private void reiniciarJuego() {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose();
        new Buscaminas();
    }

    /**
     * Agrega un vecino a la lista de vecinos de la casilla.
     *
     * @param vecino Casilla vecina.
     */
    public void agregarVecino(Casilla vecino) {
        vecinos.agregar(vecino);
    }

    /**
     * Obtiene la lista de vecinos de la casilla.
     *
     * @return Lista de vecinos.
     */
    public ListaEnlazada getVecinos() {
        return vecinos;
    }

    /**
     * Revela la casilla, mostrando si es una mina o el número de minas adyacentes.
     */
    public void revelar() {
        if (esMina) {
            setText("💣");
        } else {
            setText(minasAdyacentes > 0 ? String.valueOf(minasAdyacentes) : "");
        }
        setEnabled(false);
        revelada = true;
    }

    /**
     * Indica si la casilla está revelada.
     *
     * @return true si la casilla está revelada, false en caso contrario.
     */
    public boolean estaRevelada() {
        return revelada;
    }

    /**
     * Obtiene el identificador de la casilla.
     *
     * @return Identificador de la casilla.
     */
    public String getId() {
        return id;
    }

    /**
     * Indica si la casilla es una mina.
     *
     * @return true si la casilla es una mina, false en caso contrario.
     */
    public boolean esMina() {
        return esMina;
    }

    /**
     * Establece si la casilla es una mina.
     *
     * @param esMina true si la casilla es una mina, false en caso contrario.
     */
    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    /**
     * Obtiene el número de minas adyacentes.
     *
     * @return Número de minas adyacentes.
     */
    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    /**
     * Establece el número de minas adyacentes.
     *
     * @param minasAdyacentes Número de minas adyacentes.
     */
    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    /**
     * Establece si la casilla está revelada.
     *
     * @param revelada true si la casilla está revelada, false en caso contrario.
     */
    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    /**
     * Establece si la casilla está marcada con una bandera.
     *
     * @param bandera true si la casilla está marcada con una bandera, false en caso contrario.
     */
    public void setMarcadaConBandera(boolean bandera) {
        this.marcadaConBandera = bandera;
    }
}