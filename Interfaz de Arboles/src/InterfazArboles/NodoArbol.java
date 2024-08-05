package InterfazArboles;

public class NodoArbol extends Nodo {
    private NodoArbol nodoIzquierdo;
    private NodoArbol nodoDerecho;

    public NodoArbol(int valor) {
        super(valor);
    }

    public NodoArbol getNodoIzquierdo() {
        return this.nodoIzquierdo;
    }

    public void setNodoIzquierdo(NodoArbol nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    public NodoArbol getNodoDerecho() {
        return this.nodoDerecho;
    }

    public void setNodoDerecho(NodoArbol nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }

    public void insertarNodo(int _valor) {
        if (_valor < this.valor) {
            if (this.nodoIzquierdo == null) {
                this.nodoIzquierdo = new NodoArbol(_valor);
            } else {
                this.nodoIzquierdo.insertarNodo(_valor);
            }
        } else if (this.nodoDerecho == null) {
            this.nodoDerecho = new NodoArbol(_valor);
        } else {
            this.nodoDerecho.insertarNodo(_valor);
        }
    }

    public NodoArbol delete(NodoArbol root, int valor) {
        if (root == null) {
            return root;
        } else {
            if (valor < root.getValor()) {
                root.setNodoIzquierdo(this.delete(root.getNodoIzquierdo(), valor));
            } else if (valor > root.getValor()) {
                root.setNodoDerecho(this.delete(root.getNodoDerecho(), valor));
            } else {
                if (root.getNodoIzquierdo() == null) {
                    return root.getNodoDerecho();
                } else if (root.getNodoDerecho() == null) {
                    return root.getNodoIzquierdo();
                }
                root.setValor(this.minValor(root.getNodoDerecho()));
                root.setNodoDerecho(this.delete(root.getNodoDerecho(), root.getValor()));
            }
            return root;
        }
    }

    private int minValor(NodoArbol root) {
        int minv = root.getValor();
        while (root.getNodoIzquierdo() != null) {
            minv = root.getNodoIzquierdo().getValor();
            root = root.getNodoIzquierdo();
        }
        return minv;
    }
}