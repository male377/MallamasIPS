package com.mycompany.crud.test;

import com.mycompany.crud.util.HibernateUtil;
import org.hibernate.Session;

public class TestConexion {
    
    public static void main(String[] args) {
        try {
            System.out.println("Intentando conectar a la base de datos...");
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Conexión exitosa!");
            session.close();
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}