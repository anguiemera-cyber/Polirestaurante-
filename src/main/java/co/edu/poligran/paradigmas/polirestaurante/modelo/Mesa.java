/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Mesa {

    private int id;
    private int cantidad;
    private int estado;

    public Mesa() {
    }

    public Mesa(int cantidad, int estado) {
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Mesa(int id, int cantidad, int estado) {
        this(cantidad, estado);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "[" + id + "] Mesa para " + cantidad + " personas - estado: " + estado;
    }
}

