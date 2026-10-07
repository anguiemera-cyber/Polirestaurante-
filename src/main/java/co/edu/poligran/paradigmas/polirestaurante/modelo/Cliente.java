/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Cliente extends Persona {

    public Cliente(int id, String nombre) {
        super(id, nombre);
    }
    public void Ordenar(){
        System.out.println("El cliente esta ordenando");
    }
    
    public void Recibir(){
        System.out.println("El cliente esta recibiendo pedido");
    }
    
    public void Cancelar(){
        System.out.println("El cliente cancelo el pedido");
    }
    
    public void Pagar(){
        System.out.println("El cliente esta pagando el pedido");
    }
}

