package com.mycompany.crud.dao.impl;

import com.mycompany.crud.dao.CitaMedicaDAO;
import com.mycompany.crud.model.CitaMedica;
import com.mycompany.crud.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CitaMedicaDAOImpl extends GenericDAOImpl<CitaMedica, Integer> implements CitaMedicaDAO {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<CitaMedica> findByPaciente(Integer dniPaciente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CitaMedica> citas = null;
        try {
            Query<CitaMedica> query = session.createQuery("FROM CitaMedica c WHERE c.paciente.dni = :dniPaciente");
            query.setParameter("dniPaciente", dniPaciente);
            citas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return citas;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<CitaMedica> findByMedico(Integer idMedico) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CitaMedica> citas = null;
        try {
            Query<CitaMedica> query = session.createQuery("FROM CitaMedica c WHERE c.medico.idMedico = :idMedico");
            query.setParameter("idMedico", idMedico);
            citas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return citas;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<CitaMedica> findByFecha(Date fecha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CitaMedica> citas = null;
        try {
            Query<CitaMedica> query = session.createQuery("FROM CitaMedica c WHERE DATE(c.fechaHora) = :fecha");
            query.setParameter("fecha", fecha);
            citas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return citas;
    }
}