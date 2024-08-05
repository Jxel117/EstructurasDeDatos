package InterfazArboles;

public class ArbolAVL extends Arbol {

    @Override
    public void agregarNodo(int valor) {
        nodoRaiz = insertarAVL(nodoRaiz, valor);
    }

    private NodoArbol insertarAVL(NodoArbol nodo, int valor) {
        if (nodo == null) {
            return new NodoArbol(valor);
        }
        if (valor < nodo.getValor()) {
            nodo.setNodoIzquierdo(insertarAVL(nodo.getNodoIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setNodoDerecho(insertarAVL(nodo.getNodoDerecho(), valor));
        } else {
            return nodo;
        }

        nodo = balancear(nodo);
        return nodo;
    }

    private NodoArbol balancear(NodoArbol nodo) {
        int balance = getBalance(nodo);
        if (balance > 1) {
            if (getBalance(nodo.getNodoIzquierdo()) < 0) {
                nodo.setNodoIzquierdo(rotarIzquierda(nodo.getNodoIzquierdo()));
            }
            nodo = rotarDerecha(nodo);
        } else if (balance < -1) {
            if (getBalance(nodo.getNodoDerecho()) > 0) {
                nodo.setNodoDerecho(rotarDerecha(nodo.getNodoDerecho()));
            }
            nodo = rotarIzquierda(nodo);
        }
        return nodo;
    }

    private NodoArbol rotarDerecha(NodoArbol y) {
        NodoArbol x = y.getNodoIzquierdo();
        NodoArbol T2 = x.getNodoDerecho();

        x.setNodoDerecho(y);
        y.setNodoIzquierdo(T2);

        return x;
    }

    private NodoArbol rotarIzquierda(NodoArbol x) {
        NodoArbol y = x.getNodoDerecho();
        NodoArbol T2 = y.getNodoIzquierdo();

        y.setNodoIzquierdo(x);
        x.setNodoDerecho(T2);

        return y;
    }

    private int getBalance(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getNodoIzquierdo()) - altura(nodo.getNodoDerecho());
    }

    private int altura(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.getNodoIzquierdo()), altura(nodo.getNodoDerecho()));
    }
}
