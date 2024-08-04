import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    private ArbolBinario arbolBinario;

    public void setArbolBinario(ArbolBinario arbolBinario) {
        this.arbolBinario = arbolBinario;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbolBinario != null && arbolBinario.raiz != null) {
            drawTree(g, arbolBinario.raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, NodoArbol nodo, int x, int y, int xOffset) {
        if (nodo == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(nodo.valor), x - 7, y + 5);

        if (nodo.izquierdo != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - xOffset, y + 50);
            drawTree(g, nodo.izquierdo, x - xOffset, y + 50, xOffset / 2);
        }

        if (nodo.derecho != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + xOffset, y + 50);
            drawTree(g, nodo.derecho, x + xOffset, y + 50, xOffset / 2);
        }
    }
}
