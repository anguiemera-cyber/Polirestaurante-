/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Producto{
    private int id;
    private String nombre;
    private String tipo;
    
    
    
    public Producto(String nombre, String tipo){
       this.nombre=nombre;
       this.tipo=tipo;
    }

    public Producto(int id){
        this.id=id;
    }
    
    public int getid() { return id; }
    public void setid(int id) { this.id = id; }

    public String getnombre() { return nombre; }
    public void setnombre(String nombre) { this.nombre = nombre; }

    public String gettipo() { return tipo; }
    public void settipo(String tipo) { this.tipo = tipo; }
    
    public String tostring() {
        return "[" + id + "] " + nombre + " " + tipo;
    }
}
