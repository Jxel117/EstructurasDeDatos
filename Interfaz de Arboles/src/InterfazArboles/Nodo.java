package InterfazArboles;

public class Nodo {
    protected int valor;

    public Nodo(int valor) {
        this.valor = valor;
    }

    public void mostrarValor() {
        System.out.print(this.getValor());
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
