# ForoHub

**ForoHub** es una aplicación backend desarrollada como parte del programa ONE de Alura. Su objetivo es permitir la gestión de tópicos en un foro, incluyendo la autenticación de usuarios y funcionalidades CRUD para los tópicos.

---

## Tecnologías utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3.4.1
    - Spring Data JPA
    - Spring Security
    - Spring Web
    - Validación
- **Base de datos**: MySQL
- **Gestor de migraciones**: Flyway
- **Autenticación**: JWT (JSON Web Tokens)
- **Gestor de dependencias**: Maven

---

## Características principales

- **Autenticación segura** mediante JWT.
- **CRUD de tópicos**:
    - Registro, actualización, eliminación y listado.
- **Configuración CORS**: Permite accesos controlados desde clientes externos.
- **Validación de datos** y manejo de excepciones.

---

## Estructura del proyecto

```plaintext
com.aluracursos.forohub
├── conf
│   └── CorsConfiguration.java
├── controller
│   ├── AuthController.java
│   └── TopicoController.java
├── domain
│   ├── auth
│   │   ├── DatosAuthUsuario.java
│   │   ├── Usuario.java
│   │   └── UsuarioRepository.java
│   └── topicos
│       ├── DatosActualizarTopico.java
│       ├── DatosListadoTopicos.java
│       ├── DatosRegistroTopico.java
│       ├── DatosRespuestaTopico.java
│       ├── Topico.java
│       └── TopicoRepository.java
└── infra
    └── ForohubApplication.java
```

---

## Configuración del entorno

### 1. Clona el repositorio

```bash
git clone https://github.com/Laem024/forohub.git
cd forohub
```

### 2. Configura las variables de entorno

Define las siguientes variables en tu entorno:

- `MYSQL_USER`: Usuario de la base de datos MySQL.
- `MYSQL_PASSWORD`: Contraseña del usuario de la base de datos.
- `JWT_SECRET`: Llave secreta para la generación de tokens JWT.

### 3. Configura la base de datos

1. Crea una base de datos llamada `forohub` en MySQL.
2. Las migraciones serán gestionadas automáticamente por Flyway al iniciar la aplicación.

### 4. Ejecuta la aplicación

Usa el siguiente comando para iniciar el servidor:

```bash
./mvnw spring-boot:run
```

---

## Endpoints principales

### Autenticación

- **POST** `/auth`: Genera un token JWT para un usuario autenticado.

### Tópicos

- **GET** `/topicos`: Lista todos los tópicos registrados.
- **POST** `/topicos`: Registra un nuevo tópico.
- **PUT** `/topicos`: Actualiza un tópico existente.
- **DELETE** `/topicos/{id}`: Elimina un tópico.


---

## Licencia

Este proyecto se desarrolla como parte del programa educativo ONE de Alura. El uso y distribución están sujetos a las condiciones del programa.

