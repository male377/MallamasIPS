package com.mycompany.crud.service;

import com.mycompany.crud.dao.PacienteDAO;
import com.mycompany.crud.dao.impl.PacienteDAOImpl;
import com.mycompany.crud.model.Paciente;
import java.util.List;

public class PacienteService {
    
    private final PacienteDAO pacienteDAO;
    
    public PacienteService() {
        this.pacienteDAO = new PacienteDAOImpl();
    }
    
    public Paciente findById(Integer dni) {
        return pacienteDAO.findById(dni);
    }
    
    public List<Paciente> findAll() {
        return pacienteDAO.findAll();
    }
    
    public void save(Paciente paciente) {
        pacienteDAO.save(paciente);
    }
    
    public void update(Paciente paciente) {
        pacienteDAO.update(paciente);
    }
    
    public void delete(Paciente paciente) {
        pacienteDAO.delete(paciente);
    }
    
    public void deleteById(Integer dni) {
        pacienteDAO.deleteById(dni);
    }
}