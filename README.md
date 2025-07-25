# CRUD de Clínica con Java y Hibernate

Este proyecto implementa un CRUD (Create, Read, Update, Delete) para una base de datos de clínica utilizando Java con el framework Hibernate para la persistencia de datos.

## Estructura de la Base de Datos

La base de datos "clinica" contiene las siguientes tablas:

- **especialidad**: Almacena las especialidades médicas.
- **paciente**: Almacena la información de los pacientes.
- **medico**: Almacena la información de los médicos y su especialidad.
- **cita_medica**: Almacena las citas médicas con relaciones a pacientes y médicos.

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- Servidor de aplicaciones (Tomcat, GlassFish, etc.)

## Configuración

1. **Base de Datos**:

   - Crear una base de datos MySQL llamada "clinica".
   - Ejecutar el script SQL ubicado en `src/main/resources/db_script.sql` para crear las tablas e insertar datos de prueba.

2. **Configuración de Hibernate**:
   - El archivo de configuración de Hibernate ya está configurado con usuario "root" y contraseña "2024".

## Ejecución

### En NetBeans

1. Abrir el proyecto en NetBeans.
2. Hacer clic derecho en el proyecto y seleccionar "Clean and Build".
3. Hacer clic derecho en el proyecto y seleccionar "Run" para desplegar la aplicación en el servidor configurado.

### Manualmente

1. Compilar y empaquetar el proyecto:

   ```
   mvn clean package
   ```

2. Desplegar el archivo WAR generado en `target/crud-1.0-SNAPSHOT.war` en un servidor de aplicaciones como Tomcat.

3. Acceder a la aplicación a través de la URL:
   ```
   http://localhost:8080/crud/
   ```

### Pruebas

Para verificar la conexión a la base de datos:

```
mvn compile exec:java -Dexec.mainClass="com.mycompany.crud.test.TestConexion"
```

Para probar la funcionalidad CRUD completa:

```
mvn compile exec:java -Dexec.mainClass="com.mycompany.crud.test.TestHibernate"
```

## Estructura del Proyecto

- **model**: Contiene las clases de entidad que mapean las tablas de la base de datos.
- **dao**: Contiene las interfaces DAO (Data Access Object) para cada entidad.
- **dao.impl**: Contiene las implementaciones de las interfaces DAO.
- **service**: Contiene las clases de servicio que utilizan los DAOs.
- **controller**: Contiene los servlets que manejan las peticiones HTTP.
- **test**: Contiene clases para probar la funcionalidad.
- **resources**: Contiene archivos de configuración y recursos.
- **webapp**: Contiene archivos de la aplicación web.
  - **WEB-INF/views**: Contiene las páginas JSP organizadas por entidad.

## Funcionalidades

- CRUD completo para Especialidades, Pacientes, Médicos y Citas Médicas.
- Búsqueda de médicos por especialidad.
- Búsqueda de citas por paciente, médico o fecha.
- Interfaz web completa para gestionar las entidades.
- API REST para integración con otras aplicaciones.

## Uso de la Aplicación

1. **Página Principal**: Muestra enlaces a las diferentes secciones de la aplicación.
2. **Gestión de Pacientes**: Permite crear, ver, editar y eliminar pacientes.
3. **Gestión de Médicos**: Permite crear, ver, editar y eliminar médicos.
4. **Gestión de Especialidades**: Permite crear, ver, editar y eliminar especialidades médicas.
5. **Gestión de Citas**: Permite crear, ver, editar y eliminar citas médicas.
6. **API REST**: Proporciona endpoints para acceder a los datos mediante HTTP.

## API REST

La aplicación proporciona una API REST para integración con otras aplicaciones.

### Endpoints Disponibles

#### Interfaz Web

- `GET /crud/especialidades`: Accede a la interfaz web de gestión de especialidades.
- `GET /crud/pacientes`: Accede a la interfaz web de gestión de pacientes.
- `GET /crud/medicos`: Accede a la interfaz web de gestión de médicos.
- `GET /crud/citas`: Accede a la interfaz web de gestión de citas.

#### API REST

- `GET /crud/api/especialidades`: Obtiene todas las especialidades.
- `GET /crud/api/especialidades/{id}`: Obtiene una especialidad por su ID.
- `POST /crud/api/especialidades`: Crea una nueva especialidad.
- `PUT /crud/api/especialidades/{id}`: Actualiza una especialidad existente.
- `DELETE /crud/api/especialidades/{id}`: Elimina una especialidad.

- `GET /crud/api/pacientes`: Obtiene todos los pacientes.
- `GET /crud/api/pacientes/{dni}`: Obtiene un paciente por su DNI.
- `POST /crud/api/pacientes`: Crea un nuevo paciente.
- `PUT /crud/api/pacientes/{dni}`: Actualiza un paciente existente.
- `DELETE /crud/api/pacientes/{dni}`: Elimina un paciente.

- `GET /crud/api/medicos`: Obtiene todos los médicos.
- `GET /crud/api/medicos?especialidad={idEspecialidad}`: Obtiene médicos por especialidad.
- `GET /crud/api/medicos/{id}`: Obtiene un médico por su ID.
- `POST /crud/api/medicos`: Crea un nuevo médico.
- `PUT /crud/api/medicos/{id}`: Actualiza un médico existente.
- `DELETE /crud/api/medicos/{id}`: Elimina un médico.

- `GET /crud/api/citas`: Obtiene todas las citas médicas.
- `GET /crud/api/citas?paciente={dniPaciente}`: Obtiene citas por paciente.
- `GET /crud/api/citas?medico={idMedico}`: Obtiene citas por médico.
- `GET /crud/api/citas?fecha={fecha}`: Obtiene citas por fecha (formato: yyyy-MM-dd).
- `GET /crud/api/citas/{id}`: Obtiene una cita por su ID.
- `POST /crud/api/citas`: Crea una nueva cita.
- `PUT /crud/api/citas/{id}`: Actualiza una cita existente.
- `DELETE /crud/api/citas/{id}`: Elimina una cita.
