/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;

import com.mycompany.polirestaurante.modelo.Mesero;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaG201
 */
public class MeseroDAO implements ICrudDAO<Mesero> {

    public Mesero crear(List<Mesero> lista, Mesero mesero) {
        validar(lista, mesero);
        if (mesero.getId() <= 0) {
            mesero.setId(siguienteId(lista));
        } else if (buscarPorId(lista, mesero.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una persona con id " + mesero.getId());
        }
        lista.add(mesero);
        return mesero;
    }

    @Override
    public Mesero actualizar(List<Mesero> lista, Mesero mesero) {
         validar(lista, mesero);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == mesero.getId()) {
                lista.set(i, mesero);
                return mesero;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Mesero> lista, int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public Mesero buscarPorId(List<Mesero> lista, int id) {
        for (Mesero p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Mesero> listar(List<Mesero> lista) {
       return Collections.unmodifiableList(lista);
    }

    private void validar(List<Mesero> lista, Mesero mesero) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de personas no puede ser nula");
        }
        if (mesero == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
    }

    private int siguienteId(List<Mesero> lista) {
         int max = 0;
        for (Mesero p : lista) {
            max = Math.max(max, p.getId());
        }
        return max + 1;
    
}
}
