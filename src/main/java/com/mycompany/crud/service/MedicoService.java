package com.mycompany.crud.service;

import com.mycompany.crud.dao.MedicoDAO;
import com.mycompany.crud.dao.impl.MedicoDAOImpl;
import com.mycompany.crud.model.Medico;
import java.util.List;

public class MedicoService {
    
    private final MedicoDAO medicoDAO;
    
    public MedicoService() {
        this.medicoDAO = new MedicoDAOImpl();
    }
    
    public Medico findById(Integer id) {
        return medicoDAO.findById(id);
    }
    
    public List<Medico> findAll() {
        return medicoDAO.findAll();
    }
    
    public List<Medico> findByEspecialidad(Integer idEspecialidad) {
        return medicoDAO.findByEspecialidad(idEspecialidad);
    }
    
    public void save(Medico medico) {
        medicoDAO.save(medico);
    }
    
    public void update(Medico medico) {
        medicoDAO.update(medico);
    }
    
    public void delete(Medico medico) {
        medicoDAO.delete(medico);
    }
    
    public void deleteById(Integer id) {
        medicoDAO.deleteById(id);
    }
}