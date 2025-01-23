# SpringWebProject

Se añade una capa para la invocación de los servicios de aplicación a través de los servicios RESTful básicos.

En dicha capa un controlador recepciona las peticiones básicas y las redirige hacia los servicios de aplicación, retornando el resultado de la petición.

En este proyecto se presentan:

* Ejemplos de gestión de peticiones en una API REST en función del formato de entrada/salida solicitado.
  
* Retorno de errores cuando no sea posible satisfacer correctamente la petición.
  
* Dependencias y anotaciones asociadas a un proyecto web que proporciona una API REST.
  
* Mapeo de peticiones y retorno formateado de las respuestas.
  

# Layer Architecture

La arquitectura propuesta en todo el desarrollo del proyecto es una arquitectura divididas por contextos y capas. 

Diferenciamos contextos como la gestión de clientes, productos, compras o reclamaciones. Al tiempo que establecemos algunos elementos compartidos en un contexto de SharedKernel. 

Todo contexto de los mencionados anteriormente queda diferenciado en capas que facilitan el desacoplamiento ante productos o servicios ajenos a nuestro dominio, facilitando el desarrollo de nuevas funcionalidades, la sustitucion de un producto/servicio externo por otro y las pruebas (tests) funcionales del producto, capa a capa.
