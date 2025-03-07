package buscaminas_edd_1;

import winnPanel.WIN;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

/**
 * Esta Clase presenta el tablero del juego.
 * Contiene las casillas, minas, y métodos para manejar la lógica del juego.
 */
public class Tablero extends JPanel {

    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private ListaEnlazada minasColocadas;
    private boolean usarBFS = true; // Por defecto, se usa BFS
    private int banderasDisponibles;

    // Grafo para GraphStream
    private static Graph grafoGraphStream;
    private Viewer viewer; // Visor del grafo

    // Botones para BFS, DFS y Guardar Partida
    private JButton botonBFS;
    private JButton botonDFS;
    private JButton botonGuardar;
    private JButton botonMostrarGrafo;

    private final JPanel panel = new JPanel();

    /**
     * Constructor del tablero.
     *
     * @param filas Número de filas del tablero.
     * @param columnas Número de columnas del tablero.
     * @param numMinas Número de minas en el tablero.
     */
    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.banderasDisponibles = numMinas;
        this.casillas = new Casilla[filas][columnas];
        this.minasColocadas = new ListaEnlazada();

        setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui", "swing");
        grafoGraphStream = new SingleGraph("Buscaminas");
        grafoGraphStream.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; text-size: 15px; }");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        botonMostrarGrafo = new JButton("Mostrar Gráfico");
        botonMostrarGrafo.addActionListener(e -> mostrarGrafo());
        panelBotones.add(botonMostrarGrafo);

        botonBFS = new JButton("Usar BFS");
        botonBFS.addActionListener(e -> {
            usarBFS = true;
            JOptionPane.showMessageDialog(this, "Modo BFS activado.");
        });
        panelBotones.add(botonBFS);

        botonDFS = new JButton("Usar DFS");
        botonDFS.addActionListener(e -> {
            usarBFS = false;
            JOptionPane.showMessageDialog(this, "Modo DFS activado.");
        });
        panelBotones.add(botonDFS);

        botonGuardar = new JButton("Guardar Partida");
        botonGuardar.addActionListener(e -> guardarPartida());
        panelBotones.add(botonGuardar);

        add(panelBotones, BorderLayout.NORTH);

        this.panel.setLayout(new GridLayout(filas, columnas));
        crearTablero(this.panel);
        add(this.panel, BorderLayout.CENTER);

        construirGrafo();
        colocarMinas();
        contarMinasAdyacentes();
    }

    /**
     * Constructor alternativo del tablero.
     *
     * @param casilla Casilla inicial.
     * @param numMinas Número de minas en el tablero.
     */
    public Tablero(Casilla casilla, int numMinas) {
        this.numMinas = numMinas;
        this.banderasDisponibles = numMinas;
        this.minasColocadas = new ListaEnlazada();

        setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui", "swing");
        grafoGraphStream = new SingleGraph("Buscaminas");
        grafoGraphStream.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; text-size: 15px; }");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        botonMostrarGrafo = new JButton("Mostrar Gráfico");
        botonMostrarGrafo.addActionListener(e -> mostrarGrafo());
        panelBotones.add(botonMostrarGrafo);

        botonBFS = new JButton("Usar BFS");
        botonBFS.addActionListener(e -> {
            usarBFS = true;
            JOptionPane.showMessageDialog(this, "Modo BFS activado.");
        });
        panelBotones.add(botonBFS);

        botonDFS = new JButton("Usar DFS");
        botonDFS.addActionListener(e -> {
            usarBFS = false;
            JOptionPane.showMessageDialog(this, "Modo DFS activado.");
        });
        panelBotones.add(botonDFS);

        botonGuardar = new JButton("Guardar Partida");
        botonGuardar.addActionListener(e -> guardarPartida());
        panelBotones.add(botonGuardar);

        add(panelBotones, BorderLayout.NORTH);

        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas));
        crearTablero(panelTablero);
        add(panelTablero, BorderLayout.CENTER);

        construirGrafo();
        colocarMinas();
        contarMinasAdyacentes();
    }

    /**
     * Constructor del tablero con parámetros específicos.
     *
     * @param filas Número de filas del tablero.
     * @param columnas Número de columnas del tablero.
     * @param numMinas Número de minas en el tablero.
     * @param casillas Matriz de casillas.
     * @param minasColocadas Lista de minas colocadas.
     */
    public Tablero(int filas, int columnas, int numMinas, Casilla[][] casillas, ListaEnlazada minasColocadas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.casillas = casillas;
        this.minasColocadas = minasColocadas;

        setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui", "swing");
        grafoGraphStream = new SingleGraph("Buscaminas");
        grafoGraphStream.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; text-size: 15px; }");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        botonMostrarGrafo = new JButton("Mostrar Gráfico");
        botonMostrarGrafo.addActionListener(e -> mostrarGrafo());
        panelBotones.add(botonMostrarGrafo);

        botonBFS = new JButton("Usar BFS");
        botonBFS.addActionListener(e -> {
            usarBFS = true;
            JOptionPane.showMessageDialog(this, "Modo BFS activado.");
        });
        panelBotones.add(botonBFS);

        botonDFS = new JButton("Usar DFS");
        botonDFS.addActionListener(e -> {
            usarBFS = false;
            JOptionPane.showMessageDialog(this, "Modo DFS activado.");
        });
        panelBotones.add(botonDFS);

        botonGuardar = new JButton("Guardar Partida");
        botonGuardar.addActionListener(e -> guardarPartida());
        panelBotones.add(botonGuardar);

        add(panelBotones, BorderLayout.NORTH);

        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Las variables 'filas' y 'columnas' deben ser mayores a 0");
        }

        this.panel.setLayout(new GridLayout(filas, columnas));
        this.crearTableroFromExistingTablero(this.panel);
        add(this.panel, BorderLayout.CENTER);

        if (this.panel == null) {
            throw new IllegalStateException("El panel de tablero no puede ser null");
        }

        add(this.panel, BorderLayout.CENTER);

        construirGrafo();
        contarMinasAdyacentes();
    }

    /**
     * Muestra el grafo en una ventana aparte.
     */
    public void mostrarGrafo() {
        if (viewer == null) {
            viewer = grafoGraphStream.display();
        } else {
            viewer.getDefaultView().close(viewer.getGraphicGraph());
            viewer = grafoGraphStream.display();
        }
    }

    /**
     * Agrega un nodo al grafo de GraphStream.
     *
     * @param casilla Casilla que representa el nodo.
     */
    private void agregarNodoGraphStream(Casilla casilla) {
        String id = casilla.getId();
        if (grafoGraphStream.getNode(id) == null) {
            Node nodo = grafoGraphStream.addNode(id);
            nodo.setAttribute("ui.label", id);
        }
    }

    /**
     * Agrega una arista al grafo de GraphStream.
     *
     * @param origen Casilla de origen.
     * @param destino Casilla de destino.
     */
    private void agregarAristaGraphStream(Casilla origen, Casilla destino) {
        String idArista = origen.getId() + "-" + destino.getId();
        if (grafoGraphStream.getEdge(idArista) == null) {
            grafoGraphStream.addEdge(idArista, origen.getId(), destino.getId());
        }
    }

    /**
     * Crea el tablero con casillas.
     *
     * @param panelTablero Panel donde se agregarán las casillas.
     */
    private void crearTablero(JPanel panelTablero) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String id = String.valueOf((char) ('A' + j)) + (i + 1);
                casillas[i][j] = new Casilla(id);
                panelTablero.add(casillas[i][j]);
            }
        }
    }

    /**
     * Crea el tablero a partir de una matriz de casillas existente.
     *
     * @param panelTablero Panel donde se agregarán las casillas.
     */
    private void crearTableroFromExistingTablero(JPanel panelTablero) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                panelTablero.add(casillas[i][j]);
            }
        }
    }

    /**
     * Construye el grafo de casillas adyacentes.
     */
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

    /**
     * Coloca las minas en el tablero de forma aleatoria.
     */
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

    /**
     * Cuenta el número de minas adyacentes para cada casilla.
     */
    private void contarMinasAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                if (!casilla.esMina()) {
                    int contador = 0;
                    Nodo vecino = casilla.getVecinos().getCabeza();
                    while (vecino != null) {
                        if (vecino.casilla.esMina()) {
                            contador++;
                        }
                        vecino = vecino.siguiente;
                    }
                    casilla.setMinasAdyacentes(contador);
                }
            }
        }
    }

    /**
     * Guarda la partida actual en un archivo CSV.
     */
    private void guardarPartida() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar partida");
        fileChooser.setSelectedFile(new File("partida_guardada.csv"));

        int seleccionUsuario = fileChooser.showSaveDialog(this);

        if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".csv")) {
                archivo = new File(archivo.getAbsolutePath() + ".csv");
            }

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write("col," + columnas + ",lane," + filas + ",0\n");

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Casilla casilla = casillas[i][j];
                        writer.write(
                                casilla.getId() + ","
                                + casilla.esMina() + ","
                                + casilla.estaRevelada() + ","
                                + casilla.estaMarcadaConBandera() + ","
                                + casilla.getMinasAdyacentes() + "\n"
                        );
                    }
                }

                JOptionPane.showMessageDialog(this, "Partida guardada correctamente en:\n" + archivo.getAbsolutePath(), "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Guardado cancelado.", "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Revela las casillas desde una casilla inicial utilizando BFS o DFS.
     *
     * @param inicio Casilla inicial desde donde comenzar la revelación.
     */
    public void revelarDesde(Casilla inicio) {
        if (usarBFS) {
            bfs(inicio);
        } else {
            dfs(inicio);
        }
        repaint();
        verificarVictoria();
    }

    /**
     * Realiza la búsqueda en anchura (BFS) desde una casilla inicial.
     *
     * @param inicio Casilla inicial.
     */
    private void bfs(Casilla inicio) {
        Cola cola = new Cola();
        cola.encolar(inicio);
        inicio.revelar();
        agregarNodoGraphStream(inicio);

        while (!cola.estaVacia()) {
            Casilla actual = cola.desencolar();
            if (actual.getMinasAdyacentes() == 0) {
                Nodo vecino = actual.getVecinos().getCabeza();
                while (vecino != null) {
                    Casilla v = vecino.casilla;
                    if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
                        v.revelar();
                        cola.encolar(v);
                        agregarNodoGraphStream(v);
                        agregarAristaGraphStream(actual, v);
                    }
                    vecino = vecino.siguiente;
                }
            }
        }
    }

    /**
     * Realiza la búsqueda en profundidad (DFS) desde una casilla inicial.
     *
     * @param inicio Casilla inicial.
     */
    private void dfs(Casilla inicio) {
        Pila pila = new Pila();
        pila.apilar(inicio);
        inicio.revelar();
        agregarNodoGraphStream(inicio);

        while (!pila.estaVacia()) {
            Casilla actual = pila.desapilar();
            if (actual.getMinasAdyacentes() == 0) {
                Nodo vecino = actual.getVecinos().getCabeza();
                while (vecino != null) {
                    Casilla v = vecino.casilla;
                    if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
                        v.revelar();
                        pila.apilar(v);
                        agregarNodoGraphStream(v);
                        agregarAristaGraphStream(actual, v);
                    }
                    vecino = vecino.siguiente;
                }
            }
        }
    }

    /**
     * Establece si se debe usar BFS o DFS.
     *
     * @param usarBFS true para usar BFS, false para usar DFS.
     */
    public void setUsarBFS(boolean usarBFS) {
        this.usarBFS = usarBFS;
    }

    /**
     * Verifica si todas las minas están marcadas con banderas.
     *
     * @return true si todas las minas están marcadas, false en caso contrario.
     */
    public boolean todasLasMinasMarcadas() {
        Nodo actual = minasColocadas.getCabeza();
        while (actual != null) {
            if (!actual.casilla.estaMarcadaConBandera()) {
                return false;
            }
            actual = actual.siguiente;
        }
        return true;
    }

    /**
     * Verifica si el jugador ha ganado el juego.
     */
    public void verificarVictoria() {
        boolean todasReveladas = true;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                if (!casilla.esMina() && !casilla.estaRevelada()) {
                    todasReveladas = false;
                    break;
                }
            }
            if (!todasReveladas) {
                break;
            }
        }

        boolean todasMinasMarcadas = todasLasMinasMarcadas();

        if (todasReveladas && todasMinasMarcadas) {
            WIN winPanel = new WIN();
            winPanel.setVisible(true);
            winPanel.setLocationRelativeTo(null);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    casillas[i][j].setEnabled(false);
                }
            }

            JFrame ventanaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventanaPrincipal.dispose();
        }
    }

    /**
     * Marca o desmarca una casilla con una bandera.
     *
     * @param casilla Casilla a marcar o desmarcar.
     */
    public void marcarCasillaConBandera(Casilla casilla) {
        if (!casilla.estaRevelada()) {
            if (casilla.estaMarcadaConBandera()) {
                casilla.marcarConBandera();
                actualizarBanderasDisponibles(1);
            } else if (banderasDisponibles > 0) {
                casilla.marcarConBandera();
                actualizarBanderasDisponibles(-1);
            }
            verificarVictoria();
        }
    }

    /**
     * Actualiza el número de banderas disponibles.
     *
     * @param cambio Cambio en el número de banderas disponibles.
     */
    public void actualizarBanderasDisponibles(int cambio) {
        banderasDisponibles += cambio;
    }
}