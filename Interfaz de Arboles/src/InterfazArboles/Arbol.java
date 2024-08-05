package InterfazArboles;

import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

public class Arbol extends EstructuraDato {
    public NodoArbol nodoRaiz;
    VisualizacionArbol viewTree;

    public Arbol() {}

    public void setVisualizacion(VisualizacionArbol viewTree) {
        this.viewTree = viewTree;
    }

    public NodoArbol getNodoRaiz() {
        return this.nodoRaiz;
    }

    @Override
    public void agregarNodo(int valor) {
        if (this.nodoRaiz == null) {
            this.nodoRaiz = new NodoArbol(valor);
        } else {
            this.nodoRaiz.insertarNodo(valor);
        }
    }

    @Override
    public void insertarNodo(int dato, int posicion) {}

    @Override
    public void recorrer() {}

    @Override
    public void borrarNodo(int valor) {
        if (this.nodoRaiz != null) {
            this.nodoRaiz = this.nodoRaiz.delete(this.nodoRaiz, valor);
            this.viewTree.updateView(null);
        }
    }

    @Override
    public void limpiar() {}

    @Override
    public void modificarNodo(int posicion) {}

    @Override
    public void ordenar() {}

    @Override
    public void buscarPorDato(int dato) {}

    public void recorrerPreOrden() {
        new Thread(() -> preOrden(this.nodoRaiz)).start();
    }

    private void preOrden(NodoArbol nodo) {
        if (nodo != null) {
            actualizarVista(nodo);
            System.out.print(nodo.getValor() + " - ");
            preOrden(nodo.getNodoIzquierdo());
            preOrden(nodo.getNodoDerecho());
        }
    }

    public void recorrerInOrden() {
        new Thread(() -> inOrden(this.nodoRaiz)).start();
    }

    private void inOrden(NodoArbol nodo) {
        if (nodo != null) {
            inOrden(nodo.getNodoIzquierdo());
            actualizarVista(nodo);
            System.out.print(nodo.getValor() + " _ ");
            inOrden(nodo.getNodoDerecho());
        }
    }

    public void recorrerPostOrden() {
        new Thread(() -> postOrden(this.nodoRaiz)).start();
    }

    private void postOrden(NodoArbol nodo) {
        if (nodo != null) {
            postOrden(nodo.getNodoIzquierdo());
            postOrden(nodo.getNodoDerecho());
            actualizarVista(nodo);
            System.out.print(nodo.getValor() + " - ");
        }
    }
    private void actualizarVista(NodoArbol nodo) {
    SwingUtilities.invokeLater(() -> {
        this.viewTree.updateView(nodo);
    });

    try {
        TimeUnit.SECONDS.sleep(1L); // Este tiempo de espera podría estar bloqueando la interfaz
    } catch (InterruptedException ignored) {}
    }
} 