package com.mycompany.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cita_medica")
public class CitaMedica implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;
    
    @ManyToOne
    @JoinColumn(name = "paciente_dni")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
    
    @Column(name = "num_consultorio", length = 20)
    private String numConsultorio;
    
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    
    @Column(name = "motivo_consulta", columnDefinition = "TEXT")
    private String motivoConsulta;
    
    @Column(name = "estado", length = 50)
    private String estado;
    
    public CitaMedica() {
    }
    
    public CitaMedica(Paciente paciente, Medico medico, String numConsultorio, Date fechaHora, String motivoConsulta, String estado) {
        this.paciente = paciente;
        this.medico = medico;
        this.numConsultorio = numConsultorio;
        this.fechaHora = fechaHora;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
    }
    
    public Integer getIdCita() {
        return idCita;
    }
    
    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public String getNumConsultorio() {
        return numConsultorio;
    }
    
    public void setNumConsultorio(String numConsultorio) {
        this.numConsultorio = numConsultorio;
    }
    
    public Date getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getMotivoConsulta() {
        return motivoConsulta;
    }
    
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "CitaMedica{" + "idCita=" + idCita + ", paciente=" + paciente.getNombre() + ", medico=" + medico.getNombre() + ", fechaHora=" + fechaHora + '}';
    }
}