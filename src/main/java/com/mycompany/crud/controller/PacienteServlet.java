package com.mycompany.crud.controller;

import com.mycompany.crud.model.Paciente;
import com.mycompany.crud.service.PacienteService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PacienteServlet", urlPatterns = {"/pacientes", "/pacientes/*"})
public class PacienteServlet extends HttpServlet {

    private final PacienteService pacienteService = new PacienteService();

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
                deletePaciente(request, response);
                break;
            default:
                listPacientes(request, response);
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
                savePaciente(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/pacientes");
                break;
        }
    }
    
    private void listPacientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paciente> pacientes = pacienteService.findAll();
        request.setAttribute("pacientes", pacientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pacientes/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pacientes/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int dni = Integer.parseInt(request.getParameter("dni"));
        Paciente paciente = pacienteService.findById(dni);
        request.setAttribute("paciente", paciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pacientes/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void savePaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int dni = Integer.parseInt(request.getParameter("dni"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        
        Paciente paciente = new Paciente(dni, nombre, apellido, telefono, direccion);
        
        Paciente existingPaciente = pacienteService.findById(dni);
        if (existingPaciente != null) {
            pacienteService.update(paciente);
        } else {
            pacienteService.save(paciente);
        }
        
        response.sendRedirect(request.getContextPath() + "/pacientes");
    }
    
    private void deletePaciente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int dni = Integer.parseInt(request.getParameter("dni"));
        pacienteService.deleteById(dni);
        response.sendRedirect(request.getContextPath() + "/pacientes");
    }
}