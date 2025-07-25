package com.mycompany.crud.service;

import com.mycompany.crud.dao.EspecialidadDAO;
import com.mycompany.crud.dao.impl.EspecialidadDAOImpl;
import com.mycompany.crud.model.Especialidad;
import java.util.List;

public class EspecialidadService {
    
    private final EspecialidadDAO especialidadDAO;
    
    public EspecialidadService() {
        this.especialidadDAO = new EspecialidadDAOImpl();
    }
    
    public Especialidad findById(Integer id) {
        return especialidadDAO.findById(id);
    }
    
    public List<Especialidad> findAll() {
        return especialidadDAO.findAll();
    }
    
    public void save(Especialidad especialidad) {
        especialidadDAO.save(especialidad);
    }
    
    public void update(Especialidad especialidad) {
        especialidadDAO.update(especialidad);
    }
    
    public void delete(Especialidad especialidad) {
        especialidadDAO.delete(especialidad);
    }
    
    public void deleteById(Integer id) {
        especialidadDAO.deleteById(id);
    }
}