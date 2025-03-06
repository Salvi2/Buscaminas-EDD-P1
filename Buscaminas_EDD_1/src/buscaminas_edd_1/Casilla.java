package buscaminas_edd_1;
import losePanel.Lose;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Casilla extends JButton {
    private final String id;
    private boolean esMina;
    private int minasAdyacentes;
    private boolean revelada;
    private ListaEnlazada vecinos;
    private boolean marcadaConBandera;

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
        if (SwingUtilities.isRightMouseButton(e)) { // Verificar si es clic derecho
            Tablero tablero = (Tablero) SwingUtilities.getAncestorOfClass(Tablero.class, Casilla.this);
            if (tablero != null) {
                tablero.marcarCasillaConBandera(Casilla.this); // Llamar al método del tablero
            }
        }
    }
    });
}

    public Casilla(int id, boolean esMina, boolean revelada, boolean bandera, int minasAdyacentes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Casilla() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Casilla(String id, boolean esMina, boolean revelada, boolean bandera, int minasAdyacentes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void onClic() {
            // Si la casilla está marcada con una bandera, no se permite hacer clic izquierdo
            if (marcadaConBandera){
                return; // Salir del método sin hacer nada
            }
        if (esMina) {
            setText("💣");
            setEnabled(false);

            // Crear una instancia de la clase Lose y mostrarla
            Lose loseFrame = new Lose();
            loseFrame.setVisible(true);
            loseFrame.setLocationRelativeTo(null);

            // Opcional: Cerrar la ventana actual del juego si es necesario
            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.dispose();
        } else {
            Tablero tablero = (Tablero) SwingUtilities.getAncestorOfClass(Tablero.class, this);
            if (tablero != null) {
                tablero.revelarDesde(this);
            }
        }
    }
    
    public void marcarConBandera() {
    if (!revelada) { // Solo se puede marcar si no está revelada
        marcadaConBandera = !marcadaConBandera;
        setText(marcadaConBandera ? "🚩" : id); // Usamos un emoji de bandera
    }
    }
    
    public boolean estaMarcadaConBandera() {
    return marcadaConBandera;
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

    public void setRevelada(boolean revelada) {
       this.revelada = revelada;
    }

    public void setMarcadaConBandera(boolean bandera) {
        this.marcadaConBandera = bandera;
    }
}