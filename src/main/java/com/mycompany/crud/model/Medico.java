package com.mycompany.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "apellido", length = 100)
    private String apellido;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaMedica> citas = new ArrayList<>();
    
    public Medico() {
    }
    
    public Medico(String nombre, String apellido, Especialidad especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }
    
    public Integer getIdMedico() {
        return idMedico;
    }
    
    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public List<CitaMedica> getCitas() {
        return citas;
    }
    
    public void setCitas(List<CitaMedica> citas) {
        this.citas = citas;
    }
    
    @Override
    public String toString() {
        return "Medico{" + "idMedico=" + idMedico + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}