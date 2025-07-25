<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Médicos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
                        <h2>Gestión de Médicos</h2>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-light">Volver al Inicio</a>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <a href="${pageContext.request.contextPath}/medicos/nuevo" class="btn btn-primary">Nuevo Médico</a>
                        </div>
                        
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Especialidad</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="medico" items="${medicos}">
                                    <tr>
                                        <td>${medico.idMedico}</td>
                                        <td>${medico.nombre}</td>
                                        <td>${medico.apellido}</td>
                                        <td>${medico.especialidad.nombreEspecialidad}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/medicos/editar?id=${medico.idMedico}" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="${pageContext.request.contextPath}/medicos/eliminar?id=${medico.idMedico}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este médico?')">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <!-- Datos de ejemplo (se mostrarán mientras no haya datos reales) -->
                                <c:if test="${empty medicos}">
                                    <tr>
                                        <td>1</td>
                                        <td>Roberto</td>
                                        <td>Gómez</td>
                                        <td>Cardiología</td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="#" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Laura</td>
                                        <td>Martínez</td>
                                        <td>Dermatología</td>
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