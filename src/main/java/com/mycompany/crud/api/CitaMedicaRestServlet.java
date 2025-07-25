package com.mycompany.crud.api;

import com.mycompany.crud.model.CitaMedica;
import com.mycompany.crud.service.CitaMedicaService;
import com.mycompany.crud.util.JsonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CitaMedicaRestServlet", urlPatterns = {"/api/citas/*"})
public class CitaMedicaRestServlet extends HttpServlet {

    private final CitaMedicaService citaMedicaService = new CitaMedicaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Verificar si hay parámetros de filtro
            String pacienteParam = request.getParameter("paciente");
            String medicoParam = request.getParameter("medico");
            String fechaParam = request.getParameter("fecha");
            
            if (pacienteParam != null && !pacienteParam.isEmpty()) {
                try {
                    int dniPaciente = Integer.parseInt(pacienteParam);
                    List<CitaMedica> citas = citaMedicaService.findByPaciente(dniPaciente);
                    JsonUtil.writeJsonResponse(response, citas);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "DNI de paciente inválido");
                    JsonUtil.writeJsonResponse(response, error);
                }
            } else if (medicoParam != null && !medicoParam.isEmpty()) {
                try {
                    int idMedico = Integer.parseInt(medicoParam);
                    List<CitaMedica> citas = citaMedicaService.findByMedico(idMedico);
                    JsonUtil.writeJsonResponse(response, citas);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "ID de médico inválido");
                    JsonUtil.writeJsonResponse(response, error);
                }
            } else if (fechaParam != null && !fechaParam.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = sdf.parse(fechaParam);
                    List<CitaMedica> citas = citaMedicaService.findByFecha(fecha);
                    JsonUtil.writeJsonResponse(response, citas);
                } catch (ParseException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Formato de fecha inválido. Use yyyy-MM-dd");
                    JsonUtil.writeJsonResponse(response, error);
                }
            } else {
                // Listar todas las citas
                List<CitaMedica> citas = citaMedicaService.findAll();
                JsonUtil.writeJsonResponse(response, citas);
            }
        } else {
            // Obtener una cita por ID
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                CitaMedica cita = citaMedicaService.findById(id);
                
                if (cita != null) {
                    JsonUtil.writeJsonResponse(response, cita);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Cita médica no encontrada con ID: " + id);
                    JsonUtil.writeJsonResponse(response, error);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, String> error = new HashMap<>();
                error.put("error", "ID inválido");
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
        
        // Convertir JSON a objeto CitaMedica
        CitaMedica cita = JsonUtil.parseJsonRequest(sb.toString(), CitaMedica.class);
        
        // Guardar la cita
        citaMedicaService.save(cita);
        
        // Devolver la cita creada con su ID
        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonUtil.writeJsonResponse(response, cita);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Se requiere ID para actualizar");
            JsonUtil.writeJsonResponse(response, error);
            return;
        }
        
        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            CitaMedica existingCita = citaMedicaService.findById(id);
            
            if (existingCita == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Cita médica no encontrada con ID: " + id);
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
            
            // Convertir JSON a objeto CitaMedica
            CitaMedica cita = JsonUtil.parseJsonRequest(sb.toString(), CitaMedica.class);
            cita.setIdCita(id);
            
            // Actualizar la cita
            citaMedicaService.update(cita);
            
            // Devolver la cita actualizada
            JsonUtil.writeJsonResponse(response, cita);
            
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "ID inválido");
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
            error.put("error", "Se requiere ID para eliminar");
            JsonUtil.writeJsonResponse(response, error);
            return;
        }
        
        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            CitaMedica existingCita = citaMedicaService.findById(id);
            
            if (existingCita == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Cita médica no encontrada con ID: " + id);
                JsonUtil.writeJsonResponse(response, error);
                return;
            }
            
            // Eliminar la cita
            citaMedicaService.deleteById(id);
            
            // Devolver respuesta exitosa
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, String> error = new HashMap<>();
            error.put("error", "ID inválido");
            JsonUtil.writeJsonResponse(response, error);
        }
    }
}