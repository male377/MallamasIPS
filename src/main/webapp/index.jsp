<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Gestión de Clínica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h2 class="text-center">Sistema de Gestión de Clínica</h2>
                    </div>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card mb-3">
                                    <div class="card-header bg-info text-white">
                                        <h4>Gestión de Pacientes</h4>
                                    </div>
                                    <div class="card-body">
                                        <p>Administre la información de los pacientes.</p>
                                        <a href="${pageContext.request.contextPath}/pacientes" class="btn btn-primary">Ir a Pacientes</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card mb-3">
                                    <div class="card-header bg-success text-white">
                                        <h4>Gestión de Médicos</h4>
                                    </div>
                                    <div class="card-body">
                                        <p>Administre la información de los médicos.</p>
                                        <a href="${pageContext.request.contextPath}/medicos" class="btn btn-primary">Ir a Médicos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card mb-3">
                                    <div class="card-header bg-warning">
                                        <h4>Gestión de Especialidades</h4>
                                    </div>
                                    <div class="card-body">
                                        <p>Administre las especialidades médicas.</p>
                                        <a href="${pageContext.request.contextPath}/especialidades" class="btn btn-primary">Ir a Especialidades</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card mb-3">
                                    <div class="card-header bg-danger text-white">
                                        <h4>Gestión de Citas</h4>
                                    </div>
                                    <div class="card-body">
                                        <p>Administre las citas médicas.</p>
                                        <a href="${pageContext.request.contextPath}/citas" class="btn btn-primary">Ir a Citas</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer text-center">
                        <p>Sistema de Gestión de Clínica &copy; 2025</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>