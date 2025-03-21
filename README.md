# 🎯 **Taller de Java: Gestión de Equipos y Jugadores de Fútbol**  

📌 **Descripción**  
Este proyecto es un taller práctico en Java que permite gestionar equipos y jugadores de fútbol. Puedes trabajar con datos desde un archivo JSON o desde una base de datos MySQL. Además, incluye un menú interactivo para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y retos adicionales para analizar datos.  

---

## 🚀 **Estructura del Proyecto**

### 📂 **Estructura de Carpetas**

```plaintext
📦 AVA_PROJECTS
┣ 📂 baseededatos
┃ ┣ 📂 src/main/java
┃ ┃ ┣ 📂 com/baseededatos
┃ ┃ ┃ ┣ 📂 application/usecase
┃ ┃ ┃ ┃ ┣ 📄 EquipoUseCase.java
┃ ┃ ┃ ┃ ┗ 📄 JugadorUseCase.java
┃ ┃ ┃ ┣ 📂 config
┃ ┃ ┃ ┃ ┗ 📄 HexaSingleton.java
┃ ┃ ┃ ┣ 📂 domain/entity
┃ ┃ ┃ ┃ ┣ 📄 Equipo.java
┃ ┃ ┃ ┃ ┣ 📄 Jugador.java
┃ ┃ ┃ ┃ ┗ 📄 Estadisticas.java
┃ ┃ ┃ ┣ 📂 infrastructure
┃ ┃ ┃ ┃ ┣ 📄 jsonReader.java
┃ ┃ ┃ ┃ ┗ 📂 database
┃ ┃ ┃ ┃   ┣ 📄 ConnectionDb.java
┃ ┃ ┃ ┃   ┣ 📄 ConnectionFactory.java
┃ ┃ ┃ ┃   ┗ 📄 ConnMySql.java
┃ ┃ ┃ ┣ 📂 menu
┃ ┃ ┃ ┃ ┣ 📄 SubmenuJSON.java
┃ ┃ ┃ ┃ ┣ 📄 SubmenuMySQL.java
┃ ┃ ┃ ┃ ┣ 📄 RetosBase.java
┃ ┃ ┃ ┃ ┗ 📄 adicionales.java
┃ ┃ ┃ ┣ 📂 service
┃ ┃ ┃ ┃ ┣ 📄 EquipoService.java
┃ ┃ ┃ ┃ ┣ 📄 JugadorService.java
┃ ┃ ┃ ┃ ┗ 📄 Validaciones.java
┃ ┃ ┃ ┣ 📄 Main.java
┃ ┣ 📂 src/main/resources
┃ ┃ ┣ 📄 config.properties
┃ ┃ ┗ 📄 equipos_uefa.json
┃ ┣ 📄 pom.xml
┃ ┗ 📄 README.md
┣ 📂 target
┗ 📂 vscode
```

---

### 📂 **Clases Principales**

#### 🔧 **Dominio (Core)**

- **`Equipo` 🏟️**: Representa un equipo de fútbol con propiedades como nombre, año de fundación, estadísticas, jugadores y entrenador.  
- **`Jugador` ⚽**: Representa un jugador con propiedades como nombre, edad, altura, nacionalidad y posición.  
- **`Estadisticas` 📊**: Representa las estadísticas de un equipo, como partidos jugados, partidos ganados, goles a favor, etc.  

#### 🛠️ **Servicios**

- **`EquipoService` 🧑‍💻**: Contiene métodos para gestionar equipos (listar, insertar, actualizar, eliminar).  
- **`JugadorService` 🧑‍💻**: Contiene métodos para gestionar jugadores (listar, insertar, actualizar, eliminar).  
- **`Validaciones` ✅**: Proporciona métodos para validar entradas del usuario (números, texto, etc.).  

#### 🖥️ **Infraestructura**

- **`jsonReader` 📄**: Lee y deserializa el archivo JSON en objetos Java.  
- **`ConnectionDb` 🔌**: Interfaz para gestionar conexiones a la base de datos.  
- **`ConnMySql` 🗄️**: Implementación de la conexión a MySQL.  

#### 📋 **Menú**

- **`SubmenuJSON` 📝**: Menú interactivo para gestionar los retos con datos desde JSON.  
- **`SubmenuMySQL` 📝**: Menú interactivo para gestionar los retos con datos desde MySQL.  
- **`RetosBase` 📝**: Menú interactivo para gestionar los retos base (filtrar equipos, calcular promedios, etc.).  
- **`Adicionales` 📝**: Menú interactivo para gestionar los retos adicionales (encontrar jugadores más altos, contar delanteros, etc.).  

#### 🚪 **Main**

- **`Main` 🏁**: Punto de inicio de la aplicación. Contiene el menú principal y la lógica para cargar el archivo JSON.  

---

## 🚀 **Instalación y Configuración**  

📋 **Requisitos Previos**  

- **Java Development Kit (JDK) 17 o superior**: Asegúrate de tener instalado JDK 17 o una versión más reciente.  
- **MySQL**: Servidor de base de datos instalado y en ejecución.  
- **MySQL Connector/J**: Driver JDBC para MySQL. Puedes descargarlo desde [aquí](https://dev.mysql.com/downloads/connector/j/).  
- **Maven**: Para gestionar dependencias y compilar el proyecto.  

📥 **Clonar Repositorio**  

```bash
git clone https://github.com/Jhormancastella/baseededatos.git
```

🛠️ **Compilar y Ejecutar**  

1. Abre una terminal en la carpeta del proyecto.  

2. Compila el proyecto con Maven:  

   ```bash
   mvn clean install
   ```  

3. Ejecuta la aplicación:  

   ```bash
   java -jar target/baseededatos.jar
   ```  

---

## 🛠️ **Uso**

### **Menú Principal**

El menú principal ofrece dos opciones:  

1. **Retos con consumo de archivo JSON**: Operaciones con datos cargados desde un archivo JSON.  
2. **Retos con la base de datos (MySQL)**: Operaciones CRUD con datos almacenados en MySQL.  

### **Ejemplo de Ejecución**

```plaintext
╔═════════════════════════════════════════╗
          🏆 Menú Principal 🏆            
╠═════════════════════════════════════════╣
║ 1. Retos con consumo de archivo JSON    ║
║ 2. Retos con la base de datos (MySQL)   ║
║ 3. Salir                                ║
╚═════════════════════════════════════════╝
👉 Seleccione una opción: 2

╔════════════════════════════════════════════════╗
                🗃 Submenú MySQL 🗃               
╠════════════════════════════════════════════════╣
║ 1. Listar equipos (Leer)                       ║
║ 2. Insertar un nuevo equipo (Crear)            ║
║ 3. Actualizar un equipo existente (Actualizar) ║
║ 4. Eliminar un equipo (Eliminar)               ║
║ 5. Listar jugadores de un equipo (Leer)        ║
║ 6. Insertar un nuevo jugador (Crear)           ║
║ 7. Actualizar un jugador existente (Actualizar)║
║ 8. Eliminar un jugador (Eliminar)              ║
║ 9. Regresar al menú principal                  ║
╚════════════════════════════════════════════════╝
👉 Seleccione una opción: 1

📋 Listando equipos desde la base de datos...
ID: 1, Nombre: Equipo A, Año de fundación: 1990, Entrenador: Juan Pérez
```

---

## 📋 **Características**

- **Operaciones CRUD**: Permite gestionar equipos y jugadores en una base de datos MySQL.  
- **Análisis de datos**: Incluye retos adicionales para analizar estadísticas de equipos y jugadores.  
- **Conexión a MySQL**: Usa el patrón de diseño **Abstract Factory** para gestionar conexiones a la base de datos.  
- **Validaciones**: Verifica que los datos ingresados por el usuario sean válidos.  

---

## 🚨 **Estado del Ejercicio**  

sin Culminar.  

---

## 👤 **Autor**

**Jhorman Jesús Castellanos Morales**.  
