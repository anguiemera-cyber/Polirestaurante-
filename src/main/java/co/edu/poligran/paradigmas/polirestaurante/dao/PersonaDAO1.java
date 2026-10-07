/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.polirestaurante.dao;

import com.mycompany.polirestaurante.modelo.Mesero;
import com.mycompany.polirestaurante.modelo.Persona;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaG201
 */
public class PersonaDAO1 implements ICrudDAO<Persona> {

    @Override
    public Persona crear(List<Persona> lista, Persona persona) {
       validar(lista, persona);
        if (persona.getId() <= 0) {
            persona.setId(siguienteId(lista));
        } else if (buscarPorId(lista, persona.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una persona con id " + persona.getId());
        }
        lista.add(persona);
        return persona;
    }

    @Override
    public Persona actualizar(List<Persona> lista, Persona persona) {
        validar(lista, persona);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == persona.getId()) {
                lista.set(i, persona);
                return persona;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Persona> lista, int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    @Override
    public Persona buscarPorId(List<Persona> lista, int id) {
        for (Persona p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Persona> listar(List<Persona> lista) {
        return Collections.unmodifiableList(lista);
    }

    private int siguienteId(List<Persona> lista) {
        int max = 0;
        for (Persona p : lista) {
            max = Math.max(max, p.getId());
        }
        return max + 1;
    }

    private void validar(List<Persona> lista, Persona persona) {
         if (lista == null) {
            throw new IllegalArgumentException("La lista de personas no puede ser nula");
        }
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
    }
    
}
