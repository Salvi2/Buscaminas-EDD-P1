package buscaminas_edd_1;
import welcomePanel.Inicio;

/**
 * Clase principal que inicia el juego Buscaminas. 
 * Esta clase contiene el método main que inicia la interfaz de usuario del juego.
 */
public class Buscaminas {

    /**
     * Método principal que inicia la aplicación.
     * Crea una instancia de la ventana de inicio y la muestra en el centro de la pantalla.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
    } 
}