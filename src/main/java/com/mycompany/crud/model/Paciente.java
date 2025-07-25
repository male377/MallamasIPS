package com.mycompany.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "dni")
    private Integer dni;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "apellido", length = 100)
    private String apellido;
    
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Column(name = "direccion", length = 200)
    private String direccion;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaMedica> citas = new ArrayList<>();
    
    public Paciente() {
    }
    
    public Paciente(Integer dni, String nombre, String apellido, String telefono, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public Integer getDni() {
        return dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
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
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public List<CitaMedica> getCitas() {
        return citas;
    }
    
    public void setCitas(List<CitaMedica> citas) {
        this.citas = citas;
    }
    
    @Override
    public String toString() {
        return "Paciente{" + "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
}