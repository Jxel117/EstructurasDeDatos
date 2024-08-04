import java.util.ArrayList;
import java.util.List;

public abstract class Lista {
    protected Nodo cabeza;

    public Lista() {
        this.cabeza = null;
    }

    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public void insertar(int valor, int indice) {
        Nodo nuevo = new Nodo(valor);
        if (indice == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                temp = temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            temp.siguiente = nuevo;
        }
    }

    public void eliminar(int valor) {
        if (cabeza == null) return;

        if (cabeza.valor == valor) {
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null && temp.siguiente.valor != valor) {
            temp = temp.siguiente;
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
        }
    }

    public List<Integer> recorrido() {
        List<Integer> resultado = new ArrayList<>();
        Nodo temp = cabeza;
        while (temp != null) {
            resultado.add(temp.valor);
            temp = temp.siguiente;
        }
        return resultado;
    }
}
