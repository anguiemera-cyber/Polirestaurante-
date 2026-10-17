/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author anyim
 */
public class DetallePedido {
 
    private int idDetalle;
    private int idPedido;
    private double precioUnitario;
    private int cantidad;
 
    public DetallePedido() {
    }
 
    public DetallePedido(int idPedido, double precioUnitario, int cantidad) {
        this.idPedido = idPedido;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }
 
    public DetallePedido(int idDetalle, int idPedido, double precioUnitario, int cantidad) {
        this(idPedido, precioUnitario, cantidad);
        this.idDetalle = idDetalle;
    }
 
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }
 
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
 
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
 
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
 
    /**
     * Calcula el valor de la línea (precio unitario por cantidad).
     *
     * @return el subtotal del detalle
     */
    public double getSubtotal() {
        return precioUnitario * cantidad;
    }
 
    @Override
    public String toString() {
        return "[" + idDetalle + "] pedido: " + idPedido
                + " - cantidad: " + cantidad
                + " - precio unitario: " + precioUnitario
                + " - subtotal: " + getSubtotal();
    }
}
 