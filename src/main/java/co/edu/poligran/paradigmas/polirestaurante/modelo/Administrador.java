package co.edu.poligran.paradigmas.polirestaurante.modelo;

import java.util.List;



/**
 *
 * @author salaG201
 */
public class Administrador extends Persona {
    private String apellido;
    private int tipoDoc;
    private long numeroDoc;
    private long celular;
    private String correo;
    private String contraseña;
    private String usuario;


public Administrador(int id, String nombre, String apellido, int tipoDoc, long numeroDoc, long celular, String correo, String contraseña, String usuario) {
        super(id, nombre);
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numeroDoc = numeroDoc;
        this.celular = celular;
        this.correo = correo;
        this.contraseña = contraseña;
        this.usuario = usuario;
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public int getTipoDoc() { return tipoDoc; }
    public void settipoDoc(int tipoDoc) { this.tipoDoc = tipoDoc; }

    public long getNumeroDoc() { return numeroDoc; }
    public void setnumeroDoc(long numeroDoc) { this.numeroDoc = numeroDoc; }

    public long getCelular() { return celular; }
    public void setcelular(long celular) { this.celular = celular; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public void gestion(){
        System.out.println("Administrador realizando Gestion");
    }
}
