import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Árbol Binario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        // Campo de texto para ingresar valor
        JTextField valueField = new JTextField();
        panel.add(new JLabel("Valor:"));
        panel.add(valueField);

        // Botones para agregar y eliminar
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        // Agregar botones al panel
        panel.add(addButton);
        panel.add(deleteButton);

        // Área de texto para mostrar el recorrido del árbol
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        frame.getContentPane().add(BorderLayout.EAST, panel);

        // Panel para dibujar el árbol
        TreePanel treePanel = new TreePanel();
        frame.getContentPane().add(BorderLayout.CENTER, treePanel);

        frame.setVisible(true);

        // Crear el árbol binario
        ArbolBinario arbolBinario = new ArbolBinario() {
            @Override
            protected void agregarRecursivo(NodoArbol actual, NodoArbol nuevo) {
                if (nuevo.valor < actual.valor) {
                    if (actual.izquierdo == null) {
                        actual.izquierdo = nuevo;
                        nuevo.padre = actual;
                    } else {
                        agregarRecursivo(actual.izquierdo, nuevo);
                    }
                } else {
                    if (actual.derecho == null) {
                        actual.derecho = nuevo;
                        nuevo.padre = actual;
                    } else {
                        agregarRecursivo(actual.derecho, nuevo);
                    }
                }
            }
        };

        treePanel.setArbolBinario(arbolBinario);

        // Acción para agregar un valor
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(valueField.getText());
                    arbolBinario.agregar(value);
                    resultArea.setText("En Orden: " + arbolBinario.recorridoEnOrden().toString() + "\n");
                    valueField.setText("");
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor válido");
                }
            }
        });

        // Acción para eliminar un valor
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(valueField.getText());
                    arbolBinario.eliminar(value);
                    resultArea.setText("En Orden: " + arbolBinario.recorridoEnOrden().toString() + "\n");
                    valueField.setText("");
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor válido");
                }
            }
        });
    }
}
