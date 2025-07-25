<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Especialidades</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-warning d-flex justify-content-between align-items-center">
                        <h2>Gestión de Especialidades</h2>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-light">Volver al Inicio</a>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <a href="${pageContext.request.contextPath}/especialidades/nueva" class="btn btn-primary">Nueva Especialidad</a>
                        </div>
                        
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="especialidad" items="${especialidades}">
                                    <tr>
                                        <td>${especialidad.idEspecialidad}</td>
                                        <td>${especialidad.nombreEspecialidad}</td>
                                        <td>${especialidad.descripcion}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/especialidades/editar?id=${especialidad.idEspecialidad}" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="${pageContext.request.contextPath}/especialidades/eliminar?id=${especialidad.idEspecialidad}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar esta especialidad?')">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <!-- Datos de ejemplo (se mostrarán mientras no haya datos reales) -->
                                <c:if test="${empty especialidades}">
                                    <tr>
                                        <td>1</td>
                                        <td>Cardiología</td>
                                        <td>Especialidad médica que se ocupa del estudio, diagnóstico y tratamiento de las enfermedades del corazón y del sistema circulatorio.</td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="#" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Dermatología</td>
                                        <td>Especialidad médica encargada del estudio de la estructura y función de la piel.</td>
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