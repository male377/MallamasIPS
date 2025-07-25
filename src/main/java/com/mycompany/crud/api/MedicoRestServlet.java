package com.mycompany.crud.api;

import com.mycompany.crud.model.Medico;
import com.mycompany.crud.service.MedicoService;
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

@WebServlet(name = "MedicoRestServlet", urlPatterns = {"/api/medicos/*"})
public class MedicoRestServlet extends HttpServlet {

    private final MedicoService medicoService = new MedicoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Verificar si hay parámetro de especialidad
            String especialidadParam = request.getParameter("especialidad");
            
            if (especialidadParam != null && !especialidadParam.isEmpty()) {
                try {
                    int idEspecialidad = Integer.parseInt(especialidadParam);
                    List<Medico> medicos = medicoService.findByEspecialidad(idEspecialidad);
                    JsonUtil.writeJsonResponse(response, medicos);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "ID de especialidad inválido");
                    JsonUtil.writeJsonResponse(response, error);
                }
            } else {
                // Listar todos los médicos
                List<Medico> medicos = medicoService.findAll();
                JsonUtil.writeJsonResponse(response, medicos);
            }
        } else {
            // Obtener un médico por ID
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Medico medico = medicoService.findById(id);
                
                if (medico != null) {
                    JsonUtil.writeJsonResponse(response, medico);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Médico no encontrado con ID: " + id);
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
        
        // Convertir JSON a objeto Medico
        Medico medico = JsonUtil.parseJsonRequest(sb.toString(), Medico.class);
        
        // Guardar el médico
        medicoService.save(medico);
        
        // Devolver el médico creado con su ID
        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonUtil.writeJsonResponse(response, medico);
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
            Medico existingMedico = medicoService.findById(id);
            
            if (existingMedico == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Médico no encontrado con ID: " + id);
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
            
            // Convertir JSON a objeto Medico
            Medico medico = JsonUtil.parseJsonRequest(sb.toString(), Medico.class);
            medico.setIdMedico(id);
            
            // Actualizar el médico
            medicoService.update(medico);
            
            // Devolver el médico actualizado
            JsonUtil.writeJsonResponse(response, medico);
            
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
            Medico existingMedico = medicoService.findById(id);
            
            if (existingMedico == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Médico no encontrado con ID: " + id);
                JsonUtil.writeJsonResponse(response, error);
                return;
            }
            
            // Eliminar el médico
            medicoService.deleteById(id);
            
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