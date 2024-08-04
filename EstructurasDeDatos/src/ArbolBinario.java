import java.util.ArrayList;
import java.util.List;

public abstract class ArbolBinario {
    protected NodoArbol raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void agregar(int valor) {
        NodoArbol nuevo = new NodoArbol(valor);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            agregarRecursivo(raiz, nuevo);
        }
    }

    protected abstract void agregarRecursivo(NodoArbol actual, NodoArbol nuevo);

    public void insertar(int valor) {
        agregar(valor); // Para simplificación, insertar se comporta igual que agregar.
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private NodoArbol eliminarRecursivo(NodoArbol actual, int valor) {
        if (actual == null) return null;

        if (valor == actual.valor) {
            if (actual.izquierdo == null && actual.derecho == null) return null;
            if (actual.derecho == null) return actual.izquierdo;
            if (actual.izquierdo == null) return actual.derecho;

            int valorMenor = encontrarValorMenor(actual.derecho);
            actual.valor = valorMenor;
            actual.derecho = eliminarRecursivo(actual.derecho, valorMenor);
            return actual;
        }

        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
            return actual;
        }

        actual.derecho = eliminarRecursivo(actual.derecho, valor);
        return actual;
    }

    private int encontrarValorMenor(NodoArbol nodo) {
        return nodo.izquierdo == null ? nodo.valor : encontrarValorMenor(nodo.izquierdo);
    }

    public List<Integer> recorridoEnOrden() {
        List<Integer> recorrido = new ArrayList<>();
        enOrden(raiz, recorrido);
        return recorrido;
    }

    private void enOrden(NodoArbol nodo, List<Integer> recorrido) {
        if (nodo != null) {
            enOrden(nodo.izquierdo, recorrido);
            recorrido.add(nodo.valor);
            enOrden(nodo.derecho, recorrido);
        }
    }

    public List<Integer> recorridoPreOrden() {
        List<Integer> recorrido = new ArrayList<>();
        preOrden(raiz, recorrido);
        return recorrido;
    }

    private void preOrden(NodoArbol nodo, List<Integer> recorrido) {
        if (nodo != null) {
            recorrido.add(nodo.valor);
            preOrden(nodo.izquierdo, recorrido);
            preOrden(nodo.derecho, recorrido);
        }
    }

    public List<Integer> recorridoPostOrden() {
        List<Integer> recorrido = new ArrayList<>();
        postOrden(raiz, recorrido);
        return recorrido;
    }

    private void postOrden(NodoArbol nodo, List<Integer> recorrido) {
        if (nodo != null) {
            postOrden(nodo.izquierdo, recorrido);
            postOrden(nodo.derecho, recorrido);
            recorrido.add(nodo.valor);
        }
    }
}
