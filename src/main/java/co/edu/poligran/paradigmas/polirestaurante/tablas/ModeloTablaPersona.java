package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Persona;

public class ModeloTablaPersona extends ModeloTablaBase<Persona> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaPersona() {
        super("Id", "Nombre");
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Persona x = getFila(fila);

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
