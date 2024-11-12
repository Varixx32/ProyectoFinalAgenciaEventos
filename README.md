# ProyectoFinalAgenciaEventos
Proyecto final del curso master de Java impartido por Sinensia y Viewnext. Se demuestra el conocimiento sobre microservicios y el uso de java springboot
# Agencia de Eventos - Microservicios

# Descripción del Proyecto

Este proyecto es una aplicación basada en microservicios para gestionar una **agencia de eventos**. La aplicación permite la administración de eventos, artistas y reservas mediante tres microservicios: `Eventos`, `Artistas` y `Reservas`. Cada uno de estos microservicios maneja su propia base de datos y ofrece una API REST para realizar operaciones específicas sobre cada recurso. El proyecto utiliza **Spring Boot**, **Spring Data JPA**, y está documentado con **Swagger**.

# Funcionalidades

# Microservicio de Eventos
- Listar todos los eventos disponibles.
- Buscar un evento por nombre.
- Buscar un evento por ID.
- Verificar la disponibilidad de un evento.
- Crear un nuevo evento.

# Microservicio de Artistas
- Listar todos los artistas disponibles.
- Buscar un artista por nombre.
- Buscar un artista por ID.
- Verificar la disponibilidad de un artista.
- Crear un nuevo artista.

# Microservicio de Reservas
- Crear una nueva reserva (verificando la disponibilidad de evento y artista).
- Listar todas las reservas asociadas a un evento específico.

# Uso
Debemos crear la base de datos en MySQL con los scripts proporcionados en la carpeta Auxiliar de cada proyecto. La base de datos debe correr en el puerto por defecto 3306. 
Una vez creado podemos usar la documentacion y entorno de interfaz de Swagger para probar los distintos endpoints que la app tiene abiertos. 

Para ello debemos ir a las siguientes URL en cualquier navegador:
- (Eventos)
localhost:8081/swagger-ui/
- (Artistas)
localhost:8082/swagger-ui/
- (Reservas)
localhost:8083/swagger-ui/

La manera recomendable de probar la aplicacion es la siguiente: 
- Comprobar con los request Get a los endpoints que los datos de ejemplo estan introducidos correctamente
- Crear instancias de artistas eventos y reservas
- Utilizar los Get especificos (nombre o id) para comprobar que se han creado correctamente en la base de datos

# Contribucion
La contribucion a este proyecto no esta permitida ya que solamente esta pensado para probar mi nivel de comprension de los microservicios con springboot y de Java como kit de desarrollo. 

# Historia
Este proyecto fue desarrollado con el objetivo de implementar una arquitectura de microservicios con Spring Boot, centrado en la administración de eventos, artistas y reservas. Es una demostración de mis conocimientos sobre la creacion de microservicios y buenas practicas de codigo en general.
