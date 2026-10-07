/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Pedido {
    private int id;
    private int cantidad;
    Producto productopedido;
    private long costo;
    private String mesa;
    private String detalles;
  
    public Pedido(int id){
        this.id=id;
    }
    
    public Pedido(Producto productopedido, int cantidad, long costo, String mesa, String detalles){
        this.productopedido=productopedido;
        this.cantidad=cantidad;
        this.costo=costo;
        this.mesa=mesa;
        this.detalles=detalles;
    }
    
    public int getid() { return id; }
    public void setid(int id) { this.id = id; }

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
}
