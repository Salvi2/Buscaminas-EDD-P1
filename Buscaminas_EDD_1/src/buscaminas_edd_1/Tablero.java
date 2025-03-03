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

public class Tablero extends JPanel {
    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private ListaEnlazada minasColocadas;
    private boolean usarBFS = true; // Por defecto, se usa BFS
    private int banderasDisponibles;

    // Grafo para GraphStream
    private Graph grafoGraphStream;
    private Viewer viewer; // Visor del grafo

    // Botones para BFS, DFS y Guardar Partida
    private JButton botonBFS;
    private JButton botonDFS;
    private JButton botonGuardar;
    private JButton botonMostrarGrafo;

    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.banderasDisponibles = numMinas;
        this.casillas = new Casilla[filas][columnas];
        this.minasColocadas = new ListaEnlazada();

        // Configurar el layout principal
        setLayout(new BorderLayout());

        // Inicializar GraphStream
        System.setProperty("org.graphstream.ui", "swing"); // Usar Swing para la interfaz
        grafoGraphStream = new SingleGraph("Buscaminas");
        grafoGraphStream.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; text-size: 15px; }");

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        
        botonMostrarGrafo = new JButton("Mostrar Gráfico");
        botonMostrarGrafo.addActionListener(e -> mostrarGrafo());
        panelBotones.add(botonMostrarGrafo); // Agregar el botón al panel de botones

        // Botón para activar BFS
        botonBFS = new JButton("Usar BFS");
        botonBFS.addActionListener(e -> {
            usarBFS = true;
            JOptionPane.showMessageDialog(this, "Modo BFS activado.");
        });
        panelBotones.add(botonBFS);

        // Botón para activar DFS
        botonDFS = new JButton("Usar DFS");
        botonDFS.addActionListener(e -> {
            usarBFS = false;
            JOptionPane.showMessageDialog(this, "Modo DFS activado.");
        });
        panelBotones.add(botonDFS);

        // Botón para guardar partida
        botonGuardar = new JButton("Guardar Partida");
        botonGuardar.addActionListener(e -> guardarPartida());
        panelBotones.add(botonGuardar);

        // Agregar el panel de botones en la parte superior
        add(panelBotones, BorderLayout.NORTH);

        // Panel para el tablero
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas));
        crearTablero(panelTablero);
        add(panelTablero, BorderLayout.CENTER);

        construirGrafo();
        colocarMinas();
        contarMinasAdyacentes();
    }

    // Método para mostrar el grafo en una ventana aparte
    public void mostrarGrafo() {
        if (viewer == null) {
            viewer = grafoGraphStream.display(); // Mostrar el grafo por primera vez
        } else {
            // Cerrar el visor actual y abrir uno nuevo para actualizar el grafo
            viewer.getDefaultView().close(viewer.getGraphicGraph());
            viewer = grafoGraphStream.display();
        }
    }

    // Método para agregar un nodo al grafo de GraphStream
    private void agregarNodoGraphStream(Casilla casilla) {
        String id = casilla.getId();
        if (grafoGraphStream.getNode(id) == null) {
            Node nodo = grafoGraphStream.addNode(id);
            nodo.setAttribute("ui.label", id);
        }
    }

    // Método para agregar una arista al grafo de GraphStream
    private void agregarAristaGraphStream(Casilla origen, Casilla destino) {
        String idArista = origen.getId() + "-" + destino.getId();
        if (grafoGraphStream.getEdge(idArista) == null) {
            grafoGraphStream.addEdge(idArista, origen.getId(), destino.getId());
        }
    }

    private void crearTablero(JPanel panelTablero) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String id = String.valueOf((char) ('A' + j)) + (i + 1);
                casillas[i][j] = new Casilla(id);
                panelTablero.add(casillas[i][j]);
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

    private void guardarPartida() {
        // Crear un JFileChooser para que el usuario elija la ubicación y el nombre del archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar partida"); // Título del diálogo
        fileChooser.setSelectedFile(new File("partida_guardada.csv")); // Nombre por defecto del archivo

        // Mostrar el diálogo de guardado
        int seleccionUsuario = fileChooser.showSaveDialog(this);

        // Verificar si el usuario hizo clic en "Guardar"
        if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado por el usuario
            File archivo = fileChooser.getSelectedFile();

            // Asegurarse de que el archivo tenga la extensión .csv
            if (!archivo.getName().toLowerCase().endsWith(".csv")) {
                archivo = new File(archivo.getAbsolutePath() + ".csv");
            }

            // Guardar el archivo en la ubicación seleccionada
            try (FileWriter writer = new FileWriter(archivo)) {
                // Escribir el encabezado del CSV
                writer.write("ID,Mina,Revelada,Bandera,Minas Adyacentes\n");

                // Guardar el estado de cada casilla en formato CSV
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Casilla casilla = casillas[i][j];
                        writer.write(
                            casilla.getId() + "," +
                            casilla.esMina() + "," +
                            casilla.estaRevelada() + "," +
                            casilla.estaMarcadaConBandera() + "," +
                            casilla.getMinasAdyacentes() + "\n"
                        );
                    }
                }

                JOptionPane.showMessageDialog(this, "Partida guardada correctamente en:\n" + archivo.getAbsolutePath(), "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            // El usuario canceló el diálogo de guardado
            JOptionPane.showMessageDialog(this, "Guardado cancelado.", "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void revelarDesde(Casilla inicio) {
        if (usarBFS) bfs(inicio);
        else dfs(inicio);
        repaint(); // Forzar la actualización del tablero
        verificarVictoria(); // Verificar si el jugador ha ganado
    }

    private void bfs(Casilla inicio) {
    Cola cola = new Cola();
    cola.encolar(inicio);
    inicio.revelar();
    agregarNodoGraphStream(inicio); // Agregar el nodo inicial al grafo

    while (!cola.estaVacia()) {
        Casilla actual = cola.desencolar();
        if (actual.getMinasAdyacentes() == 0) {
            // Solo revelar casillas adyacentes si la casilla actual está vacía
            Nodo vecino = actual.getVecinos().getCabeza();
            while (vecino != null) {
                Casilla v = vecino.casilla;
                if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
                    v.revelar();
                    cola.encolar(v);
                    agregarNodoGraphStream(v); // Agregar el nodo al grafo
                    agregarAristaGraphStream(actual, v); // Agregar la arista
                }
                vecino = vecino.siguiente;
            }
        }
    }
    // nada
}   

    private void dfs(Casilla inicio) {
    Pila pila = new Pila();
    pila.apilar(inicio);
    inicio.revelar();
    agregarNodoGraphStream(inicio); // Agregar el nodo inicial al grafo

    while (!pila.estaVacia()) {
        Casilla actual = pila.desapilar();
        if (actual.getMinasAdyacentes() == 0) {
            // Solo revelar casillas adyacentes si la casilla actual está vacía
            Nodo vecino = actual.getVecinos().getCabeza();
            while (vecino != null) {
                Casilla v = vecino.casilla;
                if (!v.estaRevelada() && !v.esMina() && !v.estaMarcadaConBandera()) {
                    v.revelar();
                    pila.apilar(v);
                    agregarNodoGraphStream(v); // Agregar el nodo al grafo
                    agregarAristaGraphStream(actual, v); // Agregar la arista
                }
                vecino = vecino.siguiente;
            }
        }
    }
    // NO Mostrar el grafo al finalizar DFS
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

    public void actualizarBanderasDisponibles(int cambio) {
        banderasDisponibles += cambio;
    }
}