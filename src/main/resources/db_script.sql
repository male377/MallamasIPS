-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;
-- Tabla especialidad
CREATE TABLE IF NOT EXISTS especialidad (
    id_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especialidad VARCHAR(100),
    descripcion TEXT
);
-- Tabla paciente
CREATE TABLE IF NOT EXISTS paciente (
    dni INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(200)
);
-- Tabla medico
CREATE TABLE IF NOT EXISTS medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    id_especialidad INT,
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id_especialidad) ON DELETE RESTRICT ON UPDATE CASCADE
);
-- Tabla cita_medica
CREATE TABLE IF NOT EXISTS cita_medica (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    paciente_dni INT,
    id_medico INT,
    num_consultorio VARCHAR(20),
    fecha_hora DATETIME,
    motivo_consulta TEXT,
    estado VARCHAR(50),
    FOREIGN KEY (paciente_dni) REFERENCES paciente(dni) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico) ON DELETE RESTRICT ON UPDATE CASCADE
);
-- Insertar datos de prueba
INSERT INTO especialidad (nombre_especialidad, descripcion)
VALUES (
        'Cardiología',
        'Especialidad médica que se ocupa del estudio, diagnóstico y tratamiento de las enfermedades del corazón y del sistema circulatorio.'
    ),
    (
        'Dermatología',
        'Especialidad médica encargada del estudio de la estructura y función de la piel.'
    ),
    (
        'Pediatría',
        'Especialidad médica que estudia al niño y sus enfermedades.'
    );
INSERT INTO paciente (dni, nombre, apellido, telefono, direccion)
VALUES (
        12345678,
        'Juan',
        'Pérez',
        '555-123-456',
        'Av. Principal 123'
    ),
    (
        23456789,
        'María',
        'González',
        '555-234-567',
        'Calle Secundaria 456'
    ),
    (
        34567890,
        'Carlos',
        'Rodríguez',
        '555-345-678',
        'Plaza Central 789'
    );
INSERT INTO medico (nombre, apellido, id_especialidad)
VALUES ('Roberto', 'Gómez', 1),
    ('Laura', 'Martínez', 2),
    ('Fernando', 'López', 3);
INSERT INTO cita_medica (
        paciente_dni,
        id_medico,
        num_consultorio,
        fecha_hora,
        motivo_consulta,
        estado
    )
VALUES (
        12345678,
        1,
        'A-101',
        '2025-07-25 10:00:00',
        'Dolor en el pecho',
        'Programada'
    ),
    (
        23456789,
        2,
        'B-202',
        '2025-07-26 11:30:00',
        'Erupción cutánea',
        'Programada'
    ),
    (
        34567890,
        3,
        'C-303',
        '2025-07-27 09:15:00',
        'Control de rutina',
        'Programada'
    );