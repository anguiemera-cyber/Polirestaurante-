/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Cocinero {
    Persona cocinero;
    Ingrediente administrar;
    Pedido preparar;
    private String usuario;
    private String contraseña;
    
    public Cocinero(Persona cocinero, String usuario, String contraseña){
        this.cocinero=cocinero;
        this.usuario=usuario;
        this.contraseña=contraseña;
    }
    
    public Cocinero(Ingrediente administrar, Pedido preparar){
        this.preparar=preparar;
        this.administrar=administrar;
    }
    
     public int getPersona() { return Persona; }
    public void setPersona(int id) { this.Persona = Persona; }

    public int getcantidad() { return cantidad; }
    public void setcantidad(int cantidad) { this.cantidad = this.cantidad; }

    public Producto getproductopedido() { return productopedido; }
    public void setproductopedido(Producto productopedido) { this.productopedido = productopedido; }
    
    public long getcosto() { return costo; }
    public void setcosto(long costo) { this.costo = this.costo; }
    
    public String getmesa() { return mesa; }
    public void setmesa(String mesa) { this.mesa = this.mesa; }
    
    public String getdetalles() { return detalles; }
    public void setdetalles(String detalles) { this.detalles = this.detalles; }
    
    public String tostring() {
        return "[" + id + "] " + cantidad + " " + productopedido +" "+ detalles;
}
