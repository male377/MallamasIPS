package com.mycompany.crud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "especialidad")
public class Especialidad implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;
    
    @Column(name = "nombre_especialidad", length = 100)
    private String nombreEspecialidad;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    public Especialidad() {
    }
    
    public Especialidad(String nombreEspecialidad, String descripcion) {
        this.nombreEspecialidad = nombreEspecialidad;
        this.descripcion = descripcion;
    }
    
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }
    
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }
    
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Especialidad{" + "idEspecialidad=" + idEspecialidad + ", nombreEspecialidad=" + nombreEspecialidad + '}';
    }
}