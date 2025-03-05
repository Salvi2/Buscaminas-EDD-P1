/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package welcomePanel;
import buscaminas_edd_1.Casilla;
import buscaminas_edd_1.Tablero;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import winnPanel.WIN;

/**
 *
 * @author jesus
 */
public class Inicio extends javax.swing.JFrame {

    private Casilla[][] casillas;
    private int columnas;
    private int filas;

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NumeroColumnas = new javax.swing.JTextField();
        NumeroMinas = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        NumeroFilas = new javax.swing.JTextField();
        cargarPartida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("BUSCAMINAS");

        jLabel2.setText("INGRESE CANTIDAD DE COLUMNAS");

        jLabel3.setText("INGRESE CANTIDAD DE FILAS");

        jLabel4.setText("INGRESE CANTIDAD DE MINAS");

        NumeroColumnas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroColumnasActionPerformed(evt);
            }
        });

        NumeroMinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroMinasActionPerformed(evt);
            }
        });

        jButton1.setText("INICIO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        NumeroFilas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroFilasActionPerformed(evt);
            }
        });

        cargarPartida.setText("CARGAR PARTIDA");
        cargarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cargarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(124, 124, 124))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NumeroMinas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeroColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeroFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NumeroColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NumeroFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroMinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cargarPartida)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            

            // Verificar si los campos están vacíos
            if (NumeroFilas.getText().trim().isEmpty() || 
                NumeroColumnas.getText().trim().isEmpty() || 
                NumeroMinas.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa valores en todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si algún campo está vacío
            }
              // Obtener los valores ingresados por el usuario
            int filas = Integer.parseInt(NumeroFilas.getText());
            int columnas = Integer.parseInt(NumeroColumnas.getText());
            int minas = Integer.parseInt(NumeroMinas.getText());

            // Validar que los valores sean válidos
            if ( filas < 3 || filas > 10 || columnas < 3 || columnas > 10 || minas >= filas*columnas) {
                JOptionPane.showMessageDialog(this, "Valores inválidos. Asegúrate de que las filas, columnas y minas sean válidas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear una nueva ventana para el tablero
            JFrame frame = new JFrame("Buscaminas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el tablero con los valores ingresados
            Tablero tablero = new Tablero(filas, columnas, minas);
            frame.add(tablero);
            // Ajustar el tamaño de la ventana y hacerla visible
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            frame.setVisible(true);
            
             this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NumeroColumnasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroColumnasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroColumnasActionPerformed

    private void NumeroMinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroMinasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroMinasActionPerformed

    private void NumeroFilasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroFilasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroFilasActionPerformed

    private void cargarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPartidaActionPerformed
        // TODO add your handling code
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Cargar partida");

    // Mostrar el diálogo de selección de archivo
    int seleccionUsuario = fileChooser.showOpenDialog(this);

    // Verificar si el usuario seleccionó un archivo
    if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
        File archivo = fileChooser.getSelectedFile();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            boolean isFirstLine = true; // Bandera para identificar la primera línea (cabecera)

            // Leer el archivo CSV
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Ignorar la primera línea (cabecera)
                    continue;
                }

                String[] valores = line.split(",");

                // Obtener los valores de la línea
                String id = valores[0]; // ID de la casilla (ej. "A1")
                boolean esMina = Boolean.parseBoolean(valores[1]); // Mina
                boolean revelada = Boolean.parseBoolean(valores[2]); // Revelada
                boolean bandera = Boolean.parseBoolean(valores[3]); // Bandera
                int minasAdyacentes = Integer.parseInt(valores[4]); // Minas adyacentes

                // Convertir el ID de la casilla a coordenadas (fila, colu  mna)
                int fila = id.charAt(0) - 'A'; // Convertir la letra a índice (A=0, B=1, etc.)
                int columna = Integer.parseInt(id.substring(1)) - 1; // Convertir el número a índice base 0

                // Verificar que las coordenadas estén dentro de los límites del tablero
                if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
                    // Crear o actualizar la casilla con los valores del CSV
                    Casilla casilla = casillas[fila][columna];
                    casilla.setEsMina(esMina);
                    casilla.setRevelada(revelada);
                    casilla.setMarcadaConBandera(bandera);
                    casilla.setMinasAdyacentes(minasAdyacentes);

                    // Actualizar la interfaz gráfica de la casilla
                    if (revelada) {
                        casilla.revelar(); // Revelar la casilla si está marcada como revelada
                    }
                    if (bandera) {
                        casilla.marcarConBandera(); // Marcar con bandera si está marcada
                    }
                }
            }

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Partida cargada correctamente desde:\n" + archivo.getAbsolutePath(), "Cargar Partida", JOptionPane.INFORMATION_MESSAGE);

            // Iniciar el juego con el estado cargado
            iniciarJuego();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato del archivo CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else {
        // El usuario canceló la selección del archivo
        JOptionPane.showMessageDialog(this, "Carga cancelada.", "Cargar Partida", JOptionPane.INFORMATION_MESSAGE);
    }
}

        // Método para iniciar el juego con el estado cargado
        private void iniciarJuego() {
         // Habilitar todas las casillas
         for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
                casilla.setEnabled(true); // Habilitar la casilla para interacción
                if (casilla.estaRevelada()) {
                casilla.revelar(); // Revelar la casilla si está marcada como revelada
                }
                if (casilla.estaMarcadaConBandera()) {
                casilla.marcarConBandera(); // Marcar con bandera si está marcada
                }
         }
        }

    // Actualizar la interfaz gráfica
    repaint(); // Forzar la actualización del tablero

    // Verificar si el jugador ya ganó o perdió
    verificarEstadoDelJuego();
}

// Método para verificar el estado del juego después de cargar la partida
private void verificarEstadoDelJuego() {
    boolean todasLasMinasMarcadas = true;
    boolean todasLasCasillasReveladas = true;

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            Casilla casilla = casillas[i][j];
            if (casilla.esMina() && !casilla.estaMarcadaConBandera()) {
                todasLasMinasMarcadas = false;
            }
            if (!casilla.esMina() && !casilla.estaRevelada()) {
                todasLasCasillasReveladas = false;
            }
        }
    }

    if (todasLasMinasMarcadas && todasLasCasillasReveladas) {
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
    } else {
        // Si no se ha ganado, el juego continúa
        JOptionPane.showMessageDialog(this, "Partida cargada. ¡Continúa jugando!", "Partida Cargada", JOptionPane.INFORMATION_MESSAGE);
    }

    }//GEN-LAST:event_cargarPartidaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Inicio().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroColumnas;
    private javax.swing.JTextField NumeroFilas;
    private javax.swing.JTextField NumeroMinas;
    private javax.swing.JButton cargarPartida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
