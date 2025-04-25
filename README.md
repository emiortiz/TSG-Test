# Prueba Técnica

Este repositorio contiene la solución de la prueba técnica para desarrollada en Java. A continuación, se explica cómo instalar y ejecutar el proyecto.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

* **Java Development Kit (JDK):** Versión 17. Puedes descargarlo desde este [enlace](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
* **Maven:** Versión 3.9 o superior. Puedes descargarlo desde este [enlace](https://maven.apache.org/download.cgi).
* **Git:** (Opcional) Para descargar el repositorio sino se puede bajar mediante ZIP. Puedes descargarlo desde este [enlace](https://git-scm.com/downloads/win).
* **Mysql:** Version 8.0.* (Se recomienda instalar de forma full todo el paquete). Puedes descargarlo desde este [enlace](https://dev.mysql.com/downloads/installer/).

## Instalación

1.  **Clonar el repositorio o descargarlo de forma de ZIP:**
    Si aún no has clonado el repositorio, puedes hacerlo usando Git:
    ```bash
    git clone https://github.com/emiortiz/TSG-Test.git
    cd TSG-Test
    ```
2. **Cambiar las credenciales de la BD en el proyecto:**
   Ir a application.properties en \src\main\resources\application.properties y cambiar usuario y contraseña de la base de datos.

   ```application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/database_test
    spring.datasource.username= #Remplazar 
    spring.datasource.password= #Remplazar
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```

4.  **Construir el proyecto con Maven (si aplica):**
    Ejecuta el siguiente comando:
    ```bash
    mvnd clean install
    ```
    Este comando descargará las dependencias, compilará el código fuente y generará el archivo JAR ejecutable.

5.  **Crea la BD y los schemas necesarios:**
    Ejecuta el siguiente comando
    ```bash
    db_script.sh [UsuarioDB] [PasswordDB]
    ```
    Este ejecutara las acciones necesarias contra la BD para que la aplicacion pueda funcionar

## Ejecución

1.  Después de construir el proyecto con Maven , se habrá generado un archivo JAR ejecutable en el directorio `target`. El nombre del archivo será similar a `test-0.0.1-SNAPSHOT.jar`.

2.  Abre una terminal, navega hasta el directorio `target` y ejecuta el siguiente comando
    ```bash
    java -jar test-0.0.1-SNAPSHOT.jar
    ```
    Esto pondra en ejecucion la app y ocupara el puerto 8080 de su localhost
     
4. Listo ya puede probar la rest API siguiendo la documentacion que esta en este [enlace](https://emio-7045179.postman.co/workspace/My-Workspace~d631068d-c662-4814-87f3-0b376ef0ebda/collection/44400106-fe40b2ee-c69a-48ed-a782-c38e950ebdfd?action=share&creator=44400106) 


## Notas Adicionales

* **Observaciones y notas de mejora de la aplicacion**
    * Usar Spring webflux en la app evitaria el bloqueo entre peticiones y mejoria el tiempo de respuesta entra varias peticiones.
    * No devolver passwrods en metodos get (Se hizo asi por simplicidad de una prueba tecnica, en produccion sera un problema de seguridad).
    * Los certificados y la key para JWT deberian estar en variables de entorno no dentro de un codigo (En produccion seria un problema de seguridad)

¡Gracias por revisar mi prueba técnica!
