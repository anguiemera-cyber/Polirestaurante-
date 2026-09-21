/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Persona {
    private int id;
    private String nombre;
    
 public Persona(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
}
 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
