package com.mycompany.crud.dao;

import com.mycompany.crud.model.CitaMedica;
import java.util.Date;
import java.util.List;

public interface CitaMedicaDAO extends GenericDAO<CitaMedica, Integer> {
    // Métodos específicos para CitaMedica
    List<CitaMedica> findByPaciente(Integer dniPaciente);
    List<CitaMedica> findByMedico(Integer idMedico);
    List<CitaMedica> findByFecha(Date fecha);
}