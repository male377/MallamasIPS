package com.mycompany.crud.test;

import com.mycompany.crud.model.Especialidad;
import com.mycompany.crud.model.Paciente;
import com.mycompany.crud.model.Medico;
import com.mycompany.crud.model.CitaMedica;
import com.mycompany.crud.service.EspecialidadService;
import com.mycompany.crud.service.PacienteService;
import com.mycompany.crud.service.MedicoService;
import com.mycompany.crud.service.CitaMedicaService;
import java.util.Date;
import java.util.List;

public class TestHibernate {
    
    public static void main(String[] args) {
        // Crear servicios
        EspecialidadService especialidadService = new EspecialidadService();
        PacienteService pacienteService = new PacienteService();
        MedicoService medicoService = new MedicoService();
        CitaMedicaService citaMedicaService = new CitaMedicaService();
        
        // Crear y guardar especialidad
        Especialidad especialidad = new Especialidad("Cardiología", "Especialidad médica que se ocupa del estudio, diagnóstico y tratamiento de las enfermedades del corazón y del sistema circulatorio.");
        especialidadService.save(especialidad);
        System.out.println("Especialidad guardada: " + especialidad);
        
        // Crear y guardar paciente
        Paciente paciente = new Paciente(12345678, "Juan", "Pérez", "555-123-456", "Av. Principal 123");
        pacienteService.save(paciente);
        System.out.println("Paciente guardado: " + paciente);
        
        // Crear y guardar médico
        Medico medico = new Medico("Carlos", "Rodríguez", especialidad);
        medicoService.save(medico);
        System.out.println("Médico guardado: " + medico);
        
        // Crear y guardar cita médica
        CitaMedica cita = new CitaMedica(paciente, medico, "A-101", new Date(), "Dolor en el pecho", "Programada");
        citaMedicaService.save(cita);
        System.out.println("Cita médica guardada: " + cita);
        
        // Listar todas las especialidades
        List<Especialidad> especialidades = especialidadService.findAll();
        System.out.println("\nListado de especialidades:");
        for (Especialidad e : especialidades) {
            System.out.println(e);
        }
        
        // Listar todos los pacientes
        List<Paciente> pacientes = pacienteService.findAll();
        System.out.println("\nListado de pacientes:");
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
        
        // Listar todos los médicos
        List<Medico> medicos = medicoService.findAll();
        System.out.println("\nListado de médicos:");
        for (Medico m : medicos) {
            System.out.println(m);
        }
        
        // Listar todas las citas médicas
        List<CitaMedica> citas = citaMedicaService.findAll();
        System.out.println("\nListado de citas médicas:");
        for (CitaMedica c : citas) {
            System.out.println(c);
        }
        
        // Buscar citas por paciente
        List<CitaMedica> citasPaciente = citaMedicaService.findByPaciente(paciente.getDni());
        System.out.println("\nCitas del paciente " + paciente.getNombre() + ":");
        for (CitaMedica c : citasPaciente) {
            System.out.println(c);
        }
        
        // Buscar médicos por especialidad
        List<Medico> medicosEspecialidad = medicoService.findByEspecialidad(especialidad.getIdEspecialidad());
        System.out.println("\nMédicos de la especialidad " + especialidad.getNombreEspecialidad() + ":");
        for (Medico m : medicosEspecialidad) {
            System.out.println(m);
        }
        
        System.out.println("\nPrueba completada con éxito!");
    }
}