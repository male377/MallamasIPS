<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${medico != null ? 'Editar' : 'Nuevo'} Médico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h2>${medico != null ? 'Editar' : 'Nuevo'} Médico</h2>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/medicos/guardar" method="post">
                            <c:if test="${medico != null}">
                                <input type="hidden" name="id" value="${medico.idMedico}">
                            </c:if>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${medico != null ? medico.nombre : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellido" class="form-label">Apellido:</label>
                                <input type="text" class="form-control" id="apellido" name="apellido" value="${medico != null ? medico.apellido : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="idEspecialidad" class="form-label">Especialidad:</label>
                                <select class="form-select" id="idEspecialidad" name="idEspecialidad" required>
                                    <option value="">Seleccione una especialidad</option>
                                    <c:forEach var="especialidad" items="${especialidades}">
                                        <option value="${especialidad.idEspecialidad}" ${medico != null && medico.especialidad.idEspecialidad == especialidad.idEspecialidad ? 'selected' : ''}>
                                            ${especialidad.nombreEspecialidad}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/medicos" class="btn btn-secondary">Cancelar</a>
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