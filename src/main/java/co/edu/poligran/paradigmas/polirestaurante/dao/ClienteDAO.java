/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.polirestaurante.dao;

import com.mycompany.polirestaurante.modelo.Cliente;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaG201
 */
/**
 *
 * @author salaG201
 */
public class ClienteDAO implements ICrudDAO<Cliente> {

    @Override
    public Cliente crear(List<Cliente> lista, Cliente cliente) {
       validar(lista, cliente);
        if (cliente.getId() <= 0) {
            cliente.setId(siguienteId(lista));
        } else if (buscarPorId(lista, cliente.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una persona con id " + cliente.getId());
        }
        lista.add(cliente);
        return cliente;
    }
    
    @Override
    public Cliente actualizar(List<Cliente> lista, Cliente cliente) {
        validar(lista, cliente);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == cliente.getId()) {
                lista.set(i, cliente);
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Cliente> lista, int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public Cliente buscarPorId(List<Cliente> lista, int id) {
        for (Cliente p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public List<Cliente> listar(List<Cliente> lista) {
        return Collections.unmodifiableList(lista);
    }
    
    private void validar(List<Cliente> lista, Cliente cliente) {
         if (lista == null) {
            throw new IllegalArgumentException("La lista de clientes no puede ser nula");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
    }

    private int siguienteId(List<Cliente> lista) {
        int max = 0;
        for (Cliente p : lista) {
            max = Math.max(max, p.getId());
        }
        return max + 1;
    } 
}
