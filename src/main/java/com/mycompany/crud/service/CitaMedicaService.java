package com.mycompany.crud.service;

import com.mycompany.crud.dao.CitaMedicaDAO;
import com.mycompany.crud.dao.impl.CitaMedicaDAOImpl;
import com.mycompany.crud.model.CitaMedica;
import java.util.Date;
import java.util.List;

public class CitaMedicaService {
    
    private final CitaMedicaDAO citaMedicaDAO;
    
    public CitaMedicaService() {
        this.citaMedicaDAO = new CitaMedicaDAOImpl();
    }
    
    public CitaMedica findById(Integer id) {
        return citaMedicaDAO.findById(id);
    }
    
    public List<CitaMedica> findAll() {
        return citaMedicaDAO.findAll();
    }
    
    public List<CitaMedica> findByPaciente(Integer dniPaciente) {
        return citaMedicaDAO.findByPaciente(dniPaciente);
    }
    
    public List<CitaMedica> findByMedico(Integer idMedico) {
        return citaMedicaDAO.findByMedico(idMedico);
    }
    
    public List<CitaMedica> findByFecha(Date fecha) {
        return citaMedicaDAO.findByFecha(fecha);
    }
    
    public void save(CitaMedica citaMedica) {
        citaMedicaDAO.save(citaMedica);
    }
    
    public void update(CitaMedica citaMedica) {
        citaMedicaDAO.update(citaMedica);
    }
    
    public void delete(CitaMedica citaMedica) {
        citaMedicaDAO.delete(citaMedica);
    }
    
    public void deleteById(Integer id) {
        citaMedicaDAO.deleteById(id);
    }
}