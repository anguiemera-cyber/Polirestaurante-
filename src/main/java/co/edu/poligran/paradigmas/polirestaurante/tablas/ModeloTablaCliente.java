package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Cliente;

public class ModeloTablaCliente extends ModeloTablaBase<Cliente> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaCliente() {
        super("Id", "Nombre");
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Cliente x = getFila(fila);

        if (x == null) {
            return null;
        }

        switch (columna) {
            case 0: return x.getId();
            case 1: return x.getNombre();
            default: return null;
        }
    }
}
