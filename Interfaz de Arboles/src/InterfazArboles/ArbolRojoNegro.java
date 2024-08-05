package InterfazArboles;

import java.util.concurrent.TimeUnit;

public class ArbolRojoNegro extends Arbol {
    private NodoRojoNegro raiz;

    @Override
    public void agregarNodo(int valor) {
        System.out.println("Agregando nodo con valor: " + valor);
        raiz = insertar(raiz, valor);
        if (raiz != null) {
            raiz.setRojo(false);  // La raíz siempre debe ser negra
        }
    }

    private NodoRojoNegro insertar(NodoRojoNegro nodo, int valor) {
        if (nodo == null) {
            System.out.println("Insertando nuevo nodo con valor: " + valor);
            return new NodoRojoNegro(valor);
        }

        if (valor < nodo.getValor()) {
            nodo.setNodoIzquierdo(insertar((NodoRojoNegro) nodo.getNodoIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setNodoDerecho(insertar((NodoRojoNegro) nodo.getNodoDerecho(), valor));
        } else {
            System.out.println("Valor duplicado, no se inserta: " + valor);
            return nodo;
        }

        // Balanceo del árbol
        if (esRojo(nodo.getNodoDerecho()) && !esRojo(nodo.getNodoIzquierdo())) {
            nodo = rotarIzquierda(nodo);
        }
        if (esRojo(nodo.getNodoIzquierdo()) && esRojo(nodo.getNodoIzquierdo().getNodoIzquierdo())) {
            nodo = rotarDerecha(nodo);
        }
        if (esRojo(nodo.getNodoIzquierdo()) && esRojo(nodo.getNodoDerecho())) {
            cambiarColores(nodo);
        }

        return nodo;
    }

    private boolean esRojo(NodoArbol nodo) {
        if (nodo == null) {
            return false;
        }
        return ((NodoRojoNegro) nodo).esRojo();
    }

    public class NodoRojoNegro extends NodoArbol {
    private boolean rojo;  // true para rojo, false para negro

    public NodoRojoNegro(int valor) {
        super(valor);
        this.rojo = true;  // Los nuevos nodos son rojos por defecto
    }

    public boolean esRojo() {
        return rojo;
    }

    public void setRojo(boolean rojo) {
        this.rojo = rojo;
    }
}
    private NodoRojoNegro rotarIzquierda(NodoRojoNegro h) {
        System.out.println("Rotando a la izquierda el nodo con valor: " + h.getValor());
        NodoRojoNegro x = (NodoRojoNegro) h.getNodoDerecho();
        h.setNodoDerecho(x.getNodoIzquierdo());
        x.setNodoIzquierdo(h);
        x.setRojo(h.esRojo());
        h.setRojo(true);
        return x;
    }

    private NodoRojoNegro rotarDerecha(NodoRojoNegro h) {
        System.out.println("Rotando a la derecha el nodo con valor: " + h.getValor());
        NodoRojoNegro x = (NodoRojoNegro) h.getNodoIzquierdo();
        h.setNodoIzquierdo(x.getNodoDerecho());
        x.setNodoDerecho(h);
        x.setRojo(h.esRojo());
        h.setRojo(true);
        return x;
    }

    private void cambiarColores(NodoRojoNegro h) {
        System.out.println("Cambiando colores del nodo con valor: " + h.getValor());
        h.setRojo(!h.esRojo());
        ((NodoRojoNegro) h.getNodoIzquierdo()).setRojo(!((NodoRojoNegro) h.getNodoIzquierdo()).esRojo());
        ((NodoRojoNegro) h.getNodoDerecho()).setRojo(!((NodoRojoNegro) h.getNodoDerecho()).esRojo());
    }

    @Override
    public void borrarNodo(int valor) {
        if (raiz == null) {
            System.out.println("El árbol está vacío, no se puede borrar el valor: " + valor);
            return;
        }
        if (!esRojo(raiz.getNodoIzquierdo()) && !esRojo(raiz.getNodoDerecho())) {
            raiz.setRojo(true);
        }
        raiz = eliminar(raiz, valor);
        if (raiz != null) {
            raiz.setRojo(false);
        }
    }

    private NodoRojoNegro eliminar(NodoRojoNegro nodo, int valor) {
        if (valor < nodo.getValor()) {
            if (!esRojo(nodo.getNodoIzquierdo()) && !esRojo(nodo.getNodoIzquierdo().getNodoIzquierdo())) {
                nodo = moverRojoIzquierda(nodo);
            }
            nodo.setNodoIzquierdo(eliminar((NodoRojoNegro) nodo.getNodoIzquierdo(), valor));
        } else {
            if (esRojo(nodo.getNodoIzquierdo())) {
                nodo = rotarDerecha(nodo);
            }
            if (valor == nodo.getValor() && nodo.getNodoDerecho() == null) {
                return null;
            }
            if (!esRojo(nodo.getNodoDerecho()) && !esRojo(nodo.getNodoDerecho().getNodoIzquierdo())) {
                nodo = moverRojoDerecha(nodo);
            }
            if (valor == nodo.getValor()) {
                NodoRojoNegro x = min((NodoRojoNegro) nodo.getNodoDerecho());
                nodo.setValor(x.getValor());
                nodo.setNodoDerecho(eliminarMin((NodoRojoNegro) nodo.getNodoDerecho()));
            } else {
                nodo.setNodoDerecho(eliminar((NodoRojoNegro) nodo.getNodoDerecho(), valor));
            }
        }
        return balancear(nodo);
    }

    private NodoRojoNegro moverRojoIzquierda(NodoRojoNegro h) {
        cambiarColores(h);
        if (esRojo(h.getNodoDerecho().getNodoIzquierdo())) {
            h.setNodoDerecho(rotarDerecha((NodoRojoNegro) h.getNodoDerecho()));
            h = rotarIzquierda(h);
            cambiarColores(h);
        }
        return h;
    }

    private NodoRojoNegro moverRojoDerecha(NodoRojoNegro h) {
        cambiarColores(h);
        if (esRojo(h.getNodoIzquierdo().getNodoIzquierdo())) {
            h = rotarDerecha(h);
            cambiarColores(h);
        }
        return h;
    }

    private NodoRojoNegro min(NodoRojoNegro nodo) {
        while (nodo.getNodoIzquierdo() != null) {
            nodo = (NodoRojoNegro) nodo.getNodoIzquierdo();
        }
        return nodo;
    }

    private NodoRojoNegro eliminarMin(NodoRojoNegro nodo) {
        if (nodo.getNodoIzquierdo() == null) {
            return null;
        }
        if (!esRojo(nodo.getNodoIzquierdo()) && !esRojo(nodo.getNodoIzquierdo().getNodoIzquierdo())) {
            nodo = moverRojoIzquierda(nodo);
        }
        nodo.setNodoIzquierdo(eliminarMin((NodoRojoNegro) nodo.getNodoIzquierdo()));
        return balancear(nodo);
    }

    private NodoRojoNegro balancear(NodoRojoNegro nodo) {
        if (esRojo(nodo.getNodoDerecho())) {
            nodo = rotarIzquierda(nodo);
        }
        if (esRojo(nodo.getNodoIzquierdo()) && esRojo(nodo.getNodoIzquierdo().getNodoIzquierdo())) {
            nodo = rotarDerecha(nodo);
        }
        if (esRojo(nodo.getNodoIzquierdo()) && esRojo(nodo.getNodoDerecho())) {
            cambiarColores(nodo);
        }
        return nodo;
    }

    @Override
public void recorrerPreOrden() {
    preOrden(raiz);
}

private void preOrden(NodoRojoNegro nodo) {
    if (nodo != null) {
        System.out.print(nodo.getValor() + (nodo.esRojo() ? "R " : "N "));
        this.viewTree.updateView(nodo);

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException ignored) {}

        preOrden((NodoRojoNegro) nodo.getNodoIzquierdo());
        preOrden((NodoRojoNegro) nodo.getNodoDerecho());
    }
}

    @Override
    public void recorrerInOrden() {
        inOrden(raiz);
    }

    private void inOrden(NodoRojoNegro nodo) {
        if (nodo != null) {
            inOrden((NodoRojoNegro) nodo.getNodoIzquierdo());
            System.out.print(nodo.getValor() + (nodo.esRojo() ? "R " : "N "));
            this.viewTree.updateView(nodo);

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException ignored) {}

            inOrden((NodoRojoNegro) nodo.getNodoDerecho());
        }
    }

    @Override
    public void recorrerPostOrden() {
        postOrden(raiz);
    }

    private void postOrden(NodoRojoNegro nodo) {
        if (nodo != null) {
            postOrden((NodoRojoNegro) nodo.getNodoIzquierdo());
            postOrden((NodoRojoNegro) nodo.getNodoDerecho());
            System.out.print(nodo.getValor() + (nodo.esRojo() ? "R " : "N "));
            this.viewTree.updateView(nodo);

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException ignored) {}
        }
    }
}