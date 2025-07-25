<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${especialidad != null ? 'Editar' : 'Nueva'} Especialidad</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-warning">
                        <h2>${especialidad != null ? 'Editar' : 'Nueva'} Especialidad</h2>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/especialidades/guardar" method="post">
                            <c:if test="${especialidad != null}">
                                <input type="hidden" name="id" value="${especialidad.idEspecialidad}">
                            </c:if>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${especialidad != null ? especialidad.nombreEspecialidad : ''}" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripción:</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3">${especialidad != null ? especialidad.descripcion : ''}</textarea>
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/especialidades" class="btn btn-secondary">Cancelar</a>
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