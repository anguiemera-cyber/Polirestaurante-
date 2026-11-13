package co.edu.poligran.paradigmas.polirestaurante.tablas;



import co.edu.poligran.paradigmas.polirestaurante.modelo.Cocinero;
import co.edu.poligran.paradigmas.polirestaurante.modelo.Persona;

public class ModeloTablaCocinero extends ModeloTablaBase<Cocinero> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaCocinero() {
        super("Id", "Nombre", "Apellido", "Tipo Documento",
              "Número Documento", "Celular", "Correo", "Usuario");
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Cocinero x = getFila(fila);

        if (x == null) {
            return null;
        }

        Persona persona = x.getCocinero();

        switch (columna) {
            case 0:
                return persona != null ? persona.getId() : null;
            case 1:
                return persona != null ? persona.getNombre() : null;
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
