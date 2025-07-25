package com.mycompany.crud.dao.impl;

import com.mycompany.crud.dao.MedicoDAO;
import com.mycompany.crud.model.Medico;
import com.mycompany.crud.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MedicoDAOImpl extends GenericDAOImpl<Medico, Integer> implements MedicoDAO {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Medico> findByEspecialidad(Integer idEspecialidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Medico> medicos = null;
        try {
            Query<Medico> query = session.createQuery("FROM Medico m WHERE m.especialidad.idEspecialidad = :idEspecialidad");
            query.setParameter("idEspecialidad", idEspecialidad);
            medicos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return medicos;
    }
}