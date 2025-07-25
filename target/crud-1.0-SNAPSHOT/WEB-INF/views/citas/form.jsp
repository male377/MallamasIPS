<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${cita != null ? 'Editar' : 'Nueva'} Cita Médica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-danger text-white">
                        <h2>${cita != null ? 'Editar' : 'Nueva'} Cita Médica</h2>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/citas/guardar" method="post">
                            <c:if test="${cita != null}">
                                <input type="hidden" name="id" value="${cita.idCita}">
                            </c:if>
                            <div class="mb-3">
                                <label for="dniPaciente" class="form-label">Paciente:</label>
                                <select class="form-select" id="dniPaciente" name="dniPaciente" required>
                                    <option value="">Seleccione un paciente</option>
                                    <c:forEach var="paciente" items="${pacientes}">
                                        <option value="${paciente.dni}" ${cita != null && cita.paciente.dni == paciente.dni ? 'selected' : ''}>
                                            ${paciente.dni} - ${paciente.nombre} ${paciente.apellido}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="idMedico" class="form-label">Médico:</label>
                                <select class="form-select" id="idMedico" name="idMedico" required>
                                    <option value="">Seleccione un médico</option>
                                    <c:forEach var="medico" items="${medicos}">
                                        <option value="${medico.idMedico}" ${cita != null && cita.medico.idMedico == medico.idMedico ? 'selected' : ''}>
                                            ${medico.nombre} ${medico.apellido} - ${medico.especialidad.nombreEspecialidad}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="numConsultorio" class="form-label">Consultorio:</label>
                                <input type="text" class="form-control" id="numConsultorio" name="numConsultorio" value="${cita != null ? cita.numConsultorio : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaHora" class="form-label">Fecha y Hora:</label>
                                <c:set var="fechaHora" value="${cita != null ? cita.fechaHora : null}" />
                                <fmt:formatDate value="${fechaHora}" pattern="yyyy-MM-dd'T'HH:mm" var="formattedDate" />
                                <input type="datetime-local" class="form-control" id="fechaHora" name="fechaHora" value="${formattedDate}" required>
                            </div>
                            <div class="mb-3">
                                <label for="motivoConsulta" class="form-label">Motivo de Consulta:</label>
                                <textarea class="form-control" id="motivoConsulta" name="motivoConsulta" rows="3">${cita != null ? cita.motivoConsulta : ''}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado:</label>
                                <select class="form-select" id="estado" name="estado" required>
                                    <option value="Programada" ${cita != null && cita.estado == 'Programada' ? 'selected' : ''}>Programada</option>
                                    <option value="Completada" ${cita != null && cita.estado == 'Completada' ? 'selected' : ''}>Completada</option>
                                    <option value="Cancelada" ${cita != null && cita.estado == 'Cancelada' ? 'selected' : ''}>Cancelada</option>
                                </select>
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/citas" class="btn btn-secondary">Cancelar</a>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>