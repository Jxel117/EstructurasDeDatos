package InterfazArboles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VisualizacionArbol extends JPanel {
    private final Arbol arbol;
    private NodoArbol nodoActual;
    private final int radio = 15;
    private final int separacionVertical = 70;

    public VisualizacionArbol(Arbol arbol) {
        this.arbol = arbol;
    }

   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.arbol.getNodoRaiz() != null) {
        this.dibujarNodo(g, this.arbol.getNodoRaiz(), this.getWidth() / 2, 20, this.getWidth() / 4);
    }
}

public void updateView(NodoArbol nodo) {
    this.nodoActual = nodo;
    repaint();  // Asegúrate de que repaint sea llamado para actualizar la vista
}

private void dibujarNodo(Graphics g, NodoArbol nodo, int x, int y, int hSeparation) {
    if (nodo != null) {
        if (nodo.equals(nodoActual)) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(nodo.getValor()), x - 6, y + 4);

        if (nodo.getNodoIzquierdo() != null) {
            g.drawLine(x - radio, y + radio, x - hSeparation + radio, y + separacionVertical - radio);
            this.dibujarNodo(g, nodo.getNodoIzquierdo(), x - hSeparation, y + separacionVertical, hSeparation / 2);
        }

        if (nodo.getNodoDerecho() != null) {
            g.drawLine(x + radio, y + radio, x + hSeparation - radio, y + separacionVertical - radio);
            this.dibujarNodo(g, nodo.getNodoDerecho(), x + hSeparation, y + separacionVertical, hSeparation / 2);
        }
    }
    }
}