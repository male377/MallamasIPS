package com.mycompany.crud.dao;

import com.mycompany.crud.model.Medico;
import java.util.List;

public interface MedicoDAO extends GenericDAO<Medico, Integer> {
    // Métodos específicos para Medico
    List<Medico> findByEspecialidad(Integer idEspecialidad);
}