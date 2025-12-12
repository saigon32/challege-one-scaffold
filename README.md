# Proyecto Base Implementando Clean Architecture

onboardig keppri para banco:

​Andres Jurado​
Buenas tardes,

Bienvenido!! 

A continuación, te envío la documentación inicial a leer:

Hemos dividido el proceso de onboarding en dos frentes: técnico y administrativo. El enfoque administrativo estará orientado a la adopción de la metodología ágil, uso de pipelines, creación de pedidos, grupos de interés, dinámica de la célula, etc. Sin embargo, para este inicio vamos a concentrarnos en el frente técnico, donde los nuevos integrantes conocerán la arquitectura, las herramientas, los entornos y las prácticas de desarrollo necesarias para empezar a aportar valor en el proyecto.

Iniciamos con este articulo: 
- https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a





Aislando los detalles | by Andrés Mauricio Gómez P - Medium
Clean Architecture aísla al dominio de los detalles tecnológicos, aportando a la mantenibilidad y evolución de un componente de software.
medium.com
Y luego seguimos con estos videos:

- 1. Clean Architecture.mp4 https://youtu.be/neo-OuApxho
- 2. Functional and reactive programming.mp4 https://youtu.be/cdTyRHl_Neo
- 3. Functional and reactive programming - Challenge - 1 de 2.mp4 https://youtu.be/UEsGzJZ3N0Y
- 3. Functional and reactive programming - Challenge - 2 de 2.mp4 https://youtu.be/_LI263lc43E
- 4. Initializr - 1 de 2 https://youtu.be/EazM1knZUP4
- 4. Initializr - 2 de 2 plexo - AWS - ECR parte 2.mp4 https://youtu.be/MH-tKRm8K-I
- 5.1 Driven adapters - Http client.mp4 https://youtu.be/EiK1wl0E99Y
    - Mocky Designer: https://designer.mocky.io/manage
- 5.2 Driven adapters - Database (r2dbc) parte 1.mp4 https://youtu.be/qVfNRaFjPVo
- 5.2 Driven adapters - Database (r2dbc) parte 2.mp4 https://youtu.be/sRdPeMQ2J5Q
- 5.3 Driven adapters - MQ https://www.youtube.com/watch?v=ZTXQq9hxlV8
    - https://github.com/maocq/java-mq-response
    - https://github.com/bancolombia/commons-jms
- 6. Pruebas unitarias - Proyectos imperativos.mp4 https://youtu.be/iPaTD6dEP0Y
    - https://github.com/maocq/java-imperative-tests
    - https://www.baeldung.com/mockito-annotations
- 6.1 Pruebas unitarias - Proyectos reactivos.mp4 https://youtu.be/EBfpcYb5ifs
- Framework: https://github.com/bancolombia/scaffold-clean-architecture?tab=readme-ov-file#generate-driven-adapter
- https://bancolombia.github.io/scaffold-clean-architecture/
- https://www.baeldung.com/java-modelmapper

Entregable:

Onboarding Project – API Reactiva Siguiendo Clean Architecture:

Objetivo:Construir una API (Scaffold Bancolombia) que agregue información de 2 APIs externas mockeadas, tome ClientId/ClientSecret de AWS Secrets Manager (LocalStack) y persista el resultado en PostgreSQL usando R2DBC, con coverage ≥ 70% (JaCoCo), 1+ Acceptance Test (Karate) y análisis de calidad con SonarQube.

Ruta de aprendizaje sugerida (antes de codificar o en paralelo si lo prefiere)
Clean Architecture – Bancolombia (artículo de Medium).
Videos (en orden).


El tiempo estimado para este proceso de onboarding es de 1 semana. Sin embargo, considerando que en paralelo los nuevos integrantes deben gestionar aspectos administrativos (como creación de usuarios y accesos), proponemos extenderlo a 2 semanas para asegurar que todo quede correctamente habilitado y asimilado.

Gracias.

Saludos,

empezando el ejercicio:

Tu proyecto completo ya está listo para abrirse y ejecutarse en IntelliJ sin configurar nada más.
📦 Descargar el proyecto completo
👉 banca-full.zip
✔ Este ZIP incluye TODO lo que pediste:


Proyecto Gradle completo


Spring Boot 3.3 + Java 21


WebFlux + R2DBC PostgreSQL


Flyway migration (V1__create_table.sql)


LocalStack (Secrets Manager) completamente automatizado


docker-compose.yml


infra/localstack/init.sh que crea el secreto automáticamente




Servicios y controladores reales


Cliente AWS Secrets Manager (SDK v2)


Tests unitarios con StepVerifier


Jacoco listo para coverage ≥ 70%


Estructura limpia y lista para seguir extendiendo



▶ Cómo ejecutarlo en IntelliJ


Descarga el ZIP.


Descomprímelo.


En IntelliJ → Open → selecciona la carpeta.


En la raíz:
docker-compose up -d



Ejecuta la aplicación desde Application.java.


Tu microservicio estará en:
👉 http://localhost:8080/aggregate

Si quieres ahora que agregue:


Karate acceptance tests


Integración completa con las 2 APIs externas mockeadas (WireMock)


SonarQube + sonar-project.properties


Pipelines CI/CD (GitHub Actions o GitLab)


Persistencia real R2DBC con repositorios y entidades


Endpoints de negocio reales (Bancolombia Scaffold)


Solo dime “sí” o específicamente cuál módulo quieres que agregue. 

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
