<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Pacientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-info text-white d-flex justify-content-between align-items-center">
                        <h2>Gestión de Pacientes</h2>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-light">Volver al Inicio</a>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <a href="${pageContext.request.contextPath}/pacientes/nuevo" class="btn btn-primary">Nuevo Paciente</a>
                        </div>
                        
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Teléfono</th>
                                    <th>Dirección</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="paciente" items="${pacientes}">
                                    <tr>
                                        <td>${paciente.dni}</td>
                                        <td>${paciente.nombre}</td>
                                        <td>${paciente.apellido}</td>
                                        <td>${paciente.telefono}</td>
                                        <td>${paciente.direccion}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/pacientes/editar?dni=${paciente.dni}" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="${pageContext.request.contextPath}/pacientes/eliminar?dni=${paciente.dni}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este paciente?')">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <!-- Datos de ejemplo (se mostrarán mientras no haya datos reales) -->
                                <c:if test="${empty pacientes}">
                                    <tr>
                                        <td>12345678</td>
                                        <td>Juan</td>
                                        <td>Pérez</td>
                                        <td>555-123-456</td>
                                        <td>Av. Principal 123</td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-warning">Editar</a>
                                            <a href="#" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>23456789</td>
                                        <td>María</td>
                                        <td>González</td>
                                        <td>555-234-567</td>
                                        <td>Calle Secundaria 456</td>
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