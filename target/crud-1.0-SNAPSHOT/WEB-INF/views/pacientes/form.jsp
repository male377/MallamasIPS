<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${paciente != null ? 'Editar' : 'Nuevo'} Paciente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-info text-white">
                        <h2>${paciente != null ? 'Editar' : 'Nuevo'} Paciente</h2>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/pacientes/guardar" method="post">
                            <div class="mb-3">
                                <label for="dni" class="form-label">DNI:</label>
                                <input type="number" class="form-control" id="dni" name="dni" value="${paciente != null ? paciente.dni : ''}" ${paciente != null ? 'readonly' : ''} required>
                            </div>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${paciente != null ? paciente.nombre : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellido" class="form-label">Apellido:</label>
                                <input type="text" class="form-control" id="apellido" name="apellido" value="${paciente != null ? paciente.apellido : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="telefono" class="form-label">Teléfono:</label>
                                <input type="text" class="form-control" id="telefono" name="telefono" value="${paciente != null ? paciente.telefono : ''}">
                            </div>
                            <div class="mb-3">
                                <label for="direccion" class="form-label">Dirección:</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" value="${paciente != null ? paciente.direccion : ''}">
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/pacientes" class="btn btn-secondary">Cancelar</a>
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