package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Administrador;

public class ModeloTablaAdministrador extends ModeloTablaBase<Administrador> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaAdministrador() {
        super("Id", "Nombre", "Apellido", "Tipo Documento",
              "Número Documento", "Celular", "Correo", "Usuario");
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Administrador x = getFila(fila);

        if (x == null) {
            return null;
        }

        switch (columna) {
            case 0: return x.getId();
            case 1: return x.getNombre();
            case 2: return x.getApellido();
            case 3: return x.getTipoDoc();
            case 4: return x.getNumeroDoc();
            case 5: return x.getCelular();
            case 6: return x.getCorreo();
            case 7: return x.getUsuario();
            default: return null;
        }
    }
}
