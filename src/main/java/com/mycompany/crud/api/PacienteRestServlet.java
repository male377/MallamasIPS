package com.mycompany.crud.api;

import com.mycompany.crud.model.Paciente;
import com.mycompany.crud.service.PacienteService;
import com.mycompany.crud.util.JsonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PacienteRestServlet", urlPatterns = {"/api/pacientes/*"})
public class PacienteRestServlet extends HttpServlet {

    private final PacienteService pacienteService = new PacienteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Listar todos los pacientes
            List<Paciente> pacientes = pacienteService.findAll();
            JsonUtil.writeJsonResponse(response, pacientes);
        } else {
            // Obtener un paciente por DNI
            try {
                int dni = Integer.parseInt(pathInfo.substring(1));
                Paciente paciente = pacienteService.findById(dni);
                
                if (paciente != null) {
                    JsonUtil.writeJsonResponse(response, paciente);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Paciente no encontrado con DNI: " + dni);
                    JsonUtil.writeJsonResponse(response, error);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, String> error = new HashMap<>();
                error.put("error", "DNI inválido");
                JsonUtil.writeJsonResponse(response, error);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Leer el cuerpo de la solicitud
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // Convertir JSON a objeto Paciente
        Paciente paciente = JsonUtil.parseJsonRequest(sb.toString(), Paciente.class);
        
        // Verificar si ya existe un paciente con ese DNI
        Paciente existingPaciente = pacienteService.findById(paciente.getDni());
        if (existingPaciente != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ya existe un paciente con DNI: " + paciente.getDni());
            JsonUtil.writeJsonResponse(response, error);
            return;
        }
        
        // Guardar el paciente
        pacienteService.save(paciente);
        
        // Devolver el paciente creado
        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonUtil.writeJsonResponse(response, paciente);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Se requiere DNI para actualizar");
            JsonUtil.writeJsonResponse(response, error);
            return;
        }
        
        try {
            int dni = Integer.parseInt(pathInfo.substring(1));
            Paciente existingPaciente = pacienteService.findById(dni);
            
            if (existingPaciente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Paciente no encontrado con DNI: " + dni);
                JsonUtil.writeJsonResponse(response, error);
                return;
            }
            
            // Leer el cuerpo de la solicitud
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            
            // Convertir JSON a objeto Paciente
            Paciente paciente = JsonUtil.parseJsonRequest(sb.toString(), Paciente.class);
            paciente.setDni(dni);
            
            // Actualizar el paciente
            pacienteService.update(paciente);
            
            // Devolver el paciente actualizado
            JsonUtil.writeJsonResponse(response, paciente);
            
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "DNI inválido");
            JsonUtil.writeJsonResponse(response, error);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Se requiere DNI para eliminar");
            JsonUtil.writeJsonResponse(response, error);
            return;
        }
        
        try {
            int dni = Integer.parseInt(pathInfo.substring(1));
            Paciente existingPaciente = pacienteService.findById(dni);
            
            if (existingPaciente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Paciente no encontrado con DNI: " + dni);
                JsonUtil.writeJsonResponse(response, error);
                return;
            }
            
            // Eliminar el paciente
            pacienteService.deleteById(dni);
            
            // Devolver respuesta exitosa
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "DNI inválido");
            JsonUtil.writeJsonResponse(response, error);
        }
    }
}