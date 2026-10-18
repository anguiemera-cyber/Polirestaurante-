
package co.edu.poligran.paradigmas.polirestaurante.modelo;

public class Cocinero {

    private Persona cocinero;
    private Ingrediente administrar;
    private Pedido preparar;

    private String apellido;
    private int tipoDoc;
    private long numeroDoc;
    private long celular;
    private String correo;
    private String contraseña;
    private String usuario;

    public Cocinero(Persona cocinero, String apellido, int tipoDoc,
            long numeroDoc, long celular, String correo,
            String contraseña, String usuario) {

        this.cocinero = cocinero;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.celular = celular;
        this.correo = correo;
        this.contraseña = contraseña;
        this.usuario = usuario;
    }

    public Cocinero(Ingrediente administrar, Pedido preparar) {
        this.administrar = administrar;
        this.preparar = preparar;
    }

    public Persona getCocinero() {
        return cocinero;
    }

    public void setCocinero(Persona cocinero) {
        this.cocinero = cocinero;
    }

    public Ingrediente getAdministrar() {
        return administrar;
    }

    public void setAdministrar(Ingrediente administrar) {
        this.administrar = administrar;
    }

    public Pedido getPreparar() {
        return preparar;
    }

    public void setPreparar(Pedido preparar) {
        this.preparar = preparar;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public long getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(long numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cocinero: " + usuario
                + " | Apellido: " + apellido
                + " | Documento: " + numeroDoc
                + " | Correo: " + correo;
    }
}
