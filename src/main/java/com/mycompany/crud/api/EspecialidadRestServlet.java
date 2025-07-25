package com.mycompany.crud.api;

import com.mycompany.crud.model.Especialidad;
import com.mycompany.crud.service.EspecialidadService;
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

@WebServlet(name = "EspecialidadRestServlet", urlPatterns = {"/api/especialidades/*"})
public class EspecialidadRestServlet extends HttpServlet {

    private final EspecialidadService especialidadService = new EspecialidadService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // Listar todas las especialidades
            List<Especialidad> especialidades = especialidadService.findAll();
            JsonUtil.writeJsonResponse(response, especialidades);
        } else {
            // Obtener una especialidad por ID
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Especialidad especialidad = especialidadService.findById(id);
                
                if (especialidad != null) {
                    JsonUtil.writeJsonResponse(response, especialidad);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Especialidad no encontrada con ID: " + id);
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
        
        // Convertir JSON a objeto Especialidad
        Especialidad especialidad = JsonUtil.parseJsonRequest(sb.toString(), Especialidad.class);
        
        // Guardar la especialidad
        especialidadService.save(especialidad);
        
        // Devolver la especialidad creada con su ID
        response.setStatus(HttpServletResponse.SC_CREATED);
        JsonUtil.writeJsonResponse(response, especialidad);
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
            Especialidad existingEspecialidad = especialidadService.findById(id);
            
            if (existingEspecialidad == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Especialidad no encontrada con ID: " + id);
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
            
            // Convertir JSON a objeto Especialidad
            Especialidad especialidad = JsonUtil.parseJsonRequest(sb.toString(), Especialidad.class);
            especialidad.setIdEspecialidad(id);
            
            // Actualizar la especialidad
            especialidadService.update(especialidad);
            
            // Devolver la especialidad actualizada
            JsonUtil.writeJsonResponse(response, especialidad);
            
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
            Especialidad existingEspecialidad = especialidadService.findById(id);
            
            if (existingEspecialidad == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                Map<String, String> error = new HashMap<>();
                error.put("error", "Especialidad no encontrada con ID: " + id);
                JsonUtil.writeJsonResponse(response, error);
                return;
            }
            
            // Eliminar la especialidad
            especialidadService.deleteById(id);
            
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