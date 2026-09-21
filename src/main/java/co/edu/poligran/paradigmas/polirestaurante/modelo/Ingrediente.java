/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Ingrediente {
    private int id;
    private String nombre;
    private int estado;
    private int cantidad;
    private Boolean disponibilidad;
    
 public Ingrediente(){}
 
 public Ingrediente(String nombre, int estado,int cantidad,Boolean disponibilidad) {
        this.nombre = nombre;
        this.estado = estado;
        this.cantidad=cantidad;
        this.disponibilidad=disponibilidad;
    }
 
 public Ingrediente(int id, String nombre,int estado,int cantidad,Boolean disponibilidad ) {
        this(nombre,estado,cantidad,disponibilidad);
        this.id = id;
    }
 
 public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public boolean isDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - cantidad: " + cantidad
                + " - disponible: " + disponibilidad;
    }
}
