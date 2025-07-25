package com.mycompany.crud.controller;

import com.mycompany.crud.model.Especialidad;
import com.mycompany.crud.service.EspecialidadService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EspecialidadServlet", urlPatterns = {"/especialidades", "/especialidades/*"})
public class EspecialidadServlet extends HttpServlet {

    private final EspecialidadService especialidadService = new EspecialidadService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "/nueva":
                showNewForm(request, response);
                break;
            case "/editar":
                showEditForm(request, response);
                break;
            case "/eliminar":
                deleteEspecialidad(request, response);
                break;
            default:
                listEspecialidades(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "/guardar":
                saveEspecialidad(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/especialidades");
                break;
        }
    }
    
    private void listEspecialidades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Especialidad> especialidades = especialidadService.findAll();
        request.setAttribute("especialidades", especialidades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/especialidades/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/especialidades/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Especialidad especialidad = especialidadService.findById(id);
        request.setAttribute("especialidad", especialidad);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/especialidades/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void saveEspecialidad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        Especialidad especialidad = new Especialidad(nombre, descripcion);
        
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            especialidad.setIdEspecialidad(id);
            especialidadService.update(especialidad);
        } else {
            especialidadService.save(especialidad);
        }
        
        response.sendRedirect(request.getContextPath() + "/especialidades");
    }
    
    private void deleteEspecialidad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        especialidadService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/especialidades");
    }
}