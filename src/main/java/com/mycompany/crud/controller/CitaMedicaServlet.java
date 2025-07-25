package com.mycompany.crud.controller;

import com.mycompany.crud.model.CitaMedica;
import com.mycompany.crud.model.Medico;
import com.mycompany.crud.model.Paciente;
import com.mycompany.crud.service.CitaMedicaService;
import com.mycompany.crud.service.MedicoService;
import com.mycompany.crud.service.PacienteService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CitaMedicaServlet", urlPatterns = {"/citas", "/citas/*"})
public class CitaMedicaServlet extends HttpServlet {

    private final CitaMedicaService citaMedicaService = new CitaMedicaService();
    private final PacienteService pacienteService = new PacienteService();
    private final MedicoService medicoService = new MedicoService();

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
                deleteCitaMedica(request, response);
                break;
            default:
                listCitasMedicas(request, response);
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
                saveCitaMedica(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/citas");
                break;
        }
    }
    
    private void listCitasMedicas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CitaMedica> citas = citaMedicaService.findAll();
        request.setAttribute("citas", citas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/citas/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paciente> pacientes = pacienteService.findAll();
        List<Medico> medicos = medicoService.findAll();
        request.setAttribute("pacientes", pacientes);
        request.setAttribute("medicos", medicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/citas/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CitaMedica cita = citaMedicaService.findById(id);
        List<Paciente> pacientes = pacienteService.findAll();
        List<Medico> medicos = medicoService.findAll();
        request.setAttribute("cita", cita);
        request.setAttribute("pacientes", pacientes);
        request.setAttribute("medicos", medicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/citas/form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void saveCitaMedica(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String idParam = request.getParameter("id");
            int dniPaciente = Integer.parseInt(request.getParameter("dniPaciente"));
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
            String numConsultorio = request.getParameter("numConsultorio");
            String fechaHoraStr = request.getParameter("fechaHora");
            String motivoConsulta = request.getParameter("motivoConsulta");
            String estado = request.getParameter("estado");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date fechaHora = sdf.parse(fechaHoraStr);
            
            Paciente paciente = pacienteService.findById(dniPaciente);
            Medico medico = medicoService.findById(idMedico);
            
            CitaMedica cita = new CitaMedica(paciente, medico, numConsultorio, fechaHora, motivoConsulta, estado);
            
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                cita.setIdCita(id);
                citaMedicaService.update(cita);
            } else {
                citaMedicaService.save(cita);
            }
            
            response.sendRedirect(request.getContextPath() + "/citas");
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/citas");
        }
    }
    
    private void deleteCitaMedica(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        citaMedicaService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/citas");
    }
}