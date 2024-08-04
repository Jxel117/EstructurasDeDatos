public class NodoArbol {
    int valor;
    NodoArbol izquierdo, derecho, padre;

    public NodoArbol(int valor) {
        this.valor = valor;
        this.izquierdo = this.derecho = this.padre = null;
    }
}
