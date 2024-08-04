public class ListaDobleEnlazada extends Lista {
    @Override
    public void agregar(int valor) {
        NodoDoble nuevo = new NodoDoble(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoDoble temp = (NodoDoble) cabeza;
            while (temp.siguiente != null) {
                temp = (NodoDoble) temp.siguiente;
            }
            temp.siguiente = nuevo;
            nuevo.anterior = temp;
        }
    }

    @Override
    public void insertar(int valor, int indice) {
        NodoDoble nuevo = new NodoDoble(valor);
        if (indice == 0) {
            nuevo.siguiente = cabeza;
            if (cabeza != null) {
                ((NodoDoble) cabeza).anterior = nuevo;
            }
            cabeza = nuevo;
        } else {
            NodoDoble temp = (NodoDoble) cabeza;
            for (int i = 0; i < indice - 1; i++) {
                temp = (NodoDoble) temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            if (temp.siguiente != null) {
                ((NodoDoble) temp.siguiente).anterior = nuevo;
            }
            temp.siguiente = nuevo;
            nuevo.anterior = temp;
        }
    }

    @Override
    public void eliminar(int valor) {
        if (cabeza == null) return;

        if (cabeza.valor == valor) {
            cabeza = cabeza.siguiente;
            if (cabeza != null) {
                ((NodoDoble) cabeza).anterior = null;
            }
            return;
        }

        NodoDoble temp = (NodoDoble) cabeza;
        while (temp.siguiente != null && temp.siguiente.valor != valor) {
            temp = (NodoDoble) temp.siguiente;
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            if (temp.siguiente != null) {
                ((NodoDoble) temp.siguiente).anterior = temp;
            }
        }
    }
}
