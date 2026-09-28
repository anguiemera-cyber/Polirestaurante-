/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.polirestaurante.negocio;

import com.mycompany.polirestaurante.dao.AdministradorDAO;
import com.mycompany.polirestaurante.dao.ClienteDAO;
import com.mycompany.polirestaurante.dao.MeseroDAO;
import com.mycompany.polirestaurante.modelo.Administrador;
import com.mycompany.polirestaurante.modelo.Cliente;
import com.mycompany.polirestaurante.modelo.Mesero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salaG201
 */
public class PoliRestauranteManager {
    /** Lista materializada de los administradores. */
    private final List<Administrador> administradores;
    /** Lista materializada de meseros */
    private final List<Mesero> meseros;
    /** Lista materializada de los clientes */
    private final List<Cliente> clientes;

    /** DAO para las operaciones sobre administradores. */
    private final AdministradorDAO administradorDAO;

    /** DAO para las operaciones sobre meseros */
    private final MeseroDAO meseroDAO;

    /** DAO para las operaciones sobre clientes */
    private final ClienteDAO clienteDAO;
    
    public PoliRestauranteManager(){
        administradores = new ArrayList<>();
        meseros = new ArrayList<>();
        clientes = new ArrayList<>();
        
        administradorDAO = new  AdministradorDAO();
        meseroDAO = new MeseroDAO();
        clienteDAO = new ClienteDAO();
    }
    //Administradores 
 public Administrador crearAdministrador(Administrador administrador){
     return administradorDAO.crear(administradores, administrador);
 }
 public Administrador actualizarAdministrador(Administrador administrador){
     return administradorDAO.actualizar(administradores, administrador);
 }
 public boolean eliminarAdministrador(int id){
     return administradorDAO.eliminar(administradores, id);
 }
 public Administrador buscarAdministradorPorId(int id){
     return administradorDAO.buscarPorId(administradores, id);
 }  
 public List<Administrador> listarAdministradores(){
     return administradorDAO.listar(administradores);
 }
 
 // Meseros
 public Mesero crearMesero(Mesero mesero){
     return meseroDAO.crear(meseros, mesero);
 }
 public Mesero actualizarMesero(Mesero mesero){
     return meseroDAO.actualizar(meseros, mesero);
 }
 public boolean eliminarMesero(int id){
     return meseroDAO.eliminar(meseros, id);
 }
 public Mesero buscarMeseroPorId(int id){
     return meseroDAO.buscarPorId(meseros, id);
 }  
 public List<Mesero> listarMesero(){
     return meseroDAO.listar(meseros);
 }
 
 //Clientes
  public Cliente crearCliente(Cliente cliente){
     return clienteDAO.crear(clientes, cliente);
 }
 public Cliente actualizarCliente(Cliente cliente){
     return clienteDAO.actualizar(clientes, cliente);
 }
 public boolean eliminarCliente(int id){
     return clienteDAO.eliminar(clientes, id);
 }
 public Cliente buscarClientePorId(int id){
     return clienteDAO.buscarPorId(clientes, id);
 }  
 public List<Cliente> listarClientes(){
     return clienteDAO.listar(clientes);
 }
}
