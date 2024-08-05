package InterfazArboles;

public abstract class EstructuraDato {
    private String nombre;

    public EstructuraDato() {
    }

    public abstract void agregarNodo(int dato);

    public abstract void insertarNodo(int dato, int posicion);

    public abstract void recorrer();

    public abstract void borrarNodo(int posicion);

    public abstract void limpiar();

    public abstract void modificarNodo(int posicion);

    public abstract void ordenar();

    public abstract void buscarPorDato(int dato);

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

