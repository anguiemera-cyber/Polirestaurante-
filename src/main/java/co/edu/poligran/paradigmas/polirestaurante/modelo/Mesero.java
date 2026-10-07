/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Mesero {
     private String apellido;
    private int tipoDoc;
    private long numeroDoc;
    private long celular;
    private String correo;
    private String contraseña;
    private String usuario;


public Mesero(String apellido, int tipoDoc, long numeroDoc, long celular, String correo, String contraseña, String usuario) {
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
    
    public void gestionBebidas(){
        System.out.println();
    }
    
    public void asignarMesa(){
        System.out.println("El mesero va asignar una mesa");
    }
    
    public void tomaPedidos(){
        System.out.println("El mesero esta tomando el pedido");
    }
    
    public void entregaPedidos(){
        System.out.println("El mesero esta entregando el pedido");
    }
    
    public void cancelarPedidos(){
         System.out.println("El mesero va cancelar un pedido");
    }
    
    public void cobrarPedidos(){
         System.out.println("El mesero va cobrar un pedido");
    }
    
    public void entregarMenu(){
         System.out.println("El mesero va entregar el menu");
    }
    
    public void recibeMenu(){
         System.out.println("El mesero recibe el menu");
    }
    
    public void darCambio(){
         System.out.println("El mesero va a entregar las vueltas");
    }
    
    public void darInformacion(){
         System.out.println("El mesero va a dar informacion");
    }
    
    public void responderPreguntas(){
         System.out.println("El mesero va responder una pregunta");
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setId(int siguienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

