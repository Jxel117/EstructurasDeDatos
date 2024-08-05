package InterfazArboles;

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