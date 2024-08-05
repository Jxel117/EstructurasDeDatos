package InterfazArboles;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        mostrarSeleccionArbol();
    }

    private static void mostrarSeleccionArbol() {
        JFrame selectionFrame = new JFrame("Seleccionar Tipo de Árbol");
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionFrame.setSize(300, 200);

        JPanel selectionPanel = new JPanel();
        JButton simpleButton = new JButton("Árbol Binario Simple");
        JButton avlButton = new JButton("Árbol AVL");
        JButton redBlackButton = new JButton("Árbol Rojo-Negro");

        simpleButton.addActionListener(e -> {
            selectionFrame.dispose();
            launchMainFrame(new Arbol());
        });

        avlButton.addActionListener(e -> {
            selectionFrame.dispose();
            launchMainFrame(new ArbolAVL());
        });

        redBlackButton.addActionListener(e -> {
            selectionFrame.dispose();
            launchMainFrame(new ArbolRojoNegro());
        });

        selectionPanel.add(simpleButton);
        selectionPanel.add(avlButton);
        selectionPanel.add(redBlackButton);
        selectionFrame.add(selectionPanel);
        selectionFrame.setVisible(true);
    }

    private static void launchMainFrame(Arbol arbol) {
        JFrame frame = new JFrame("Árbol Binario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        VisualizacionArbol panel = new VisualizacionArbol(arbol);
        arbol.setVisualizacion(panel);
        frame.add(panel, "Center");

        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);

        JTextField valorField = new JTextField(5);
        JTextField valorEliminarField = new JTextField(5);
        JButton addButton = new JButton("Añadir Nodo");
        JButton deleteButton = new JButton("Eliminar Nodo");
        JButton preOrderButton = new JButton("Recorrer Preorden");
        JButton inOrderButton = new JButton("Recorrer Inorden");
        JButton postOrderButton = new JButton("Recorrer Postorden");
        JButton changeTreeButton = new JButton("Cambiar Árbol");

        ActionListener addListener = e -> {
            int valor = Integer.parseInt(valorField.getText());
            arbol.agregarNodo(valor);
            valorField.setText("");
            panel.repaint();
        };

        ActionListener deleteListener = e -> {
            int valor = Integer.parseInt(valorEliminarField.getText());
            arbol.borrarNodo(valor);
            valorEliminarField.setText("");
            panel.repaint();
        };

        addButton.addActionListener(addListener);
        deleteButton.addActionListener(deleteListener);

        preOrderButton.addActionListener(e -> arbol.recorrerPreOrden());
        inOrderButton.addActionListener(e -> arbol.recorrerInOrden());
        postOrderButton.addActionListener(e -> arbol.recorrerPostOrden());

        changeTreeButton.addActionListener(e -> {
            frame.dispose();
            mostrarSeleccionArbol();
        });

        valorField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addListener.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

        valorEliminarField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    deleteListener.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

        // Configurar el diseño de GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Valor:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(valorField, gbc);

        gbc.gridx = 2;
        controlPanel.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Eliminar:"), gbc);

        gbc.gridx = 1;
        controlPanel.add(valorEliminarField, gbc);

        gbc.gridx = 2;
        controlPanel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        controlPanel.add(preOrderButton, gbc);

        gbc.gridy = 3;
        controlPanel.add(inOrderButton, gbc);

        gbc.gridy = 4;
        controlPanel.add(postOrderButton, gbc);

        gbc.gridy = 5;
        controlPanel.add(changeTreeButton, gbc);

        frame.add(controlPanel, "South");
        frame.setVisible(true);
    }
}