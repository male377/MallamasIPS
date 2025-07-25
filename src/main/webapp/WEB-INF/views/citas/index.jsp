<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Citas Médicas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-danger text-white d-flex justify-content-between align-items-center">
                        <h2>Gestión de Citas Médicas</h2>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-light">Volver al Inicio</a>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <a href="${pageContext.request.contextPath}/citas/nueva" class="btn btn-primary">Nueva Cita</a>
                        </div>
                        
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Paciente</th>
                                    <th>Médico</th>
                                    <th>Consultorio</th>
                                    <th>Fecha y Hora</th>
                                    <th>Motivo</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cita" items="${citas}">
                                    <tr>
                                        <td>${cita.idCita}</td>
                                        <td>${cita.paciente.nombre} ${cita.paciente.apellido}</td>
                                        <td>${cita.medico.nombre} ${cita.medico.apellido}</td>
                                        <td>${cita.numConsultorio}</td>
                                        <td><fmt:formatDate value="${cita.fechaHora}" pattern="dd/MM/yyyy HH:mm" /></td>
                                        <td>${cita.motivoConsulta}</td>
                                        <td>
                                            <span class="badge ${cita.estado eq 'Programada' ? 'bg-primary' : cita.estado eq 'Completada' ? 'bg-success' : 'bg-danger'}">
                                                ${cita.estado}
                                            </span>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/citas/editar?id=${cita.idCita}" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="${pageContext.request.contextPath}/citas/eliminar?id=${cita.idCita}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar esta cita?')">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <!-- Datos de ejemplo (se mostrarán mientras no haya datos reales) -->
                                <c:if test="${empty citas}">
                                    <tr>
                                        <td>1</td>
                                        <td>Juan Pérez</td>
                                        <td>Roberto Gómez</td>
                                        <td>A-101</td>
                                        <td>25/07/2025 10:00</td>
                                        <td>Dolor en el pecho</td>
                                        <td><span class="badge bg-primary">Programada</span></td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="#" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>María González</td>
                                        <td>Laura Martínez</td>
                                        <td>B-202</td>
                                        <td>26/07/2025 11:30</td>
                                        <td>Erupción cutánea</td>
                                        <td><span class="badge bg-primary">Programada</span></td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="#" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>