public class ArbolRojoNegro extends ArbolBinario {
    enum Color {ROJO, NEGRO}

    class NodoRojoNegro extends NodoArbol {
        Color color;

        public NodoRojoNegro(int valor, Color color) {
            super(valor);
            this.color = color;
        }
    }

    @Override
    protected void agregarRecursivo(NodoArbol actual, NodoArbol nuevo) {
    }

}
