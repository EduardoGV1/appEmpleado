# appEmpleado
1. Descargar los proyectos
2. En el caso del microservicio, se configuro un usuario y contraseña de base de datos. El orden para levantar es (1. empleado-eureka-server, 2. empleado-gateway-server, 3. empleado). Adicionalmente el puerto en el poryecto empleado es dinamico y se puede generar mas de una instancia.
3. En el caso de la aplicacion front, se configuro la ruta de conexion basado en el puerto donde se desplego empleado-gateway-server (8090), se debe ejecutar "npm install" y "npm start" para poder levantar el proyecto.
4. En el caso de la base de datos se adjunto 3 scripts para poder crear la bd, crear la tabla y insertar algunos datos de prueba.
