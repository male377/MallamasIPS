package com.mycompany.crud.controller;

import com.mycompany.crud.model.Especialidad;
import com.mycompany.crud.model.Medico;
import com.mycompany.crud.service.EspecialidadService;
import com.mycompany.crud.service.MedicoService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MedicoServlet", urlPatterns = {"/medicos", "/medicos/*"})
public class MedicoServlet extends HttpServlet {

    private final MedicoService medicoService = new MedicoService();
    private final EspecialidadService especialidadService = new EspecialidadService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "/nuevo":
                showNewForm(request, response);
                break;
            case "/editar":
                showEditForm(request, response);
                break;
            case "/eliminar":
                deleteMedico(request, response);
                break;
            default:
                listMedicos(request, response);
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
                saveMedico(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/medicos");
                break;
        }
    }
    
    private void listMedicos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Medico> medicos = medicoService.findAll();
        request.setAttribute("medicos", medicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/medicos/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Especialidad> especialidades = especialidadService.findAll();
        request.setAttribute("especialidades", especialidades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/medicos/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Medico medico = medicoService.findById(id);
        List<Especialidad> especialidades = especialidadService.findAll();
        request.setAttribute("medico", medico);
        request.setAttribute("especialidades", especialidades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/medicos/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void saveMedico(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int idEspecialidad = Integer.parseInt(request.getParameter("idEspecialidad"));
        
        Especialidad especialidad = especialidadService.findById(idEspecialidad);
        
        Medico medico = new Medico(nombre, apellido, especialidad);
        
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            medico.setIdMedico(id);
            medicoService.update(medico);
        } else {
            medicoService.save(medico);
        }
        
        response.sendRedirect(request.getContextPath() + "/medicos");
    }
    
    private void deleteMedico(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        medicoService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/medicos");
    }
}