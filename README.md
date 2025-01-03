# Documentación de API Rest Creacion de Usuario v1.0.0
## Descripción del proyecto

Este proyecto implementa un API para creacion de Usuario. Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña",
más un listado de objetos "teléfono", el diagrama de base de datos queda de la siguiente manera.

![diagramdb.png](diagramdb.png)

## Recursos
El proyecto esta creado con las siguientes tecnologías, las primeras 4 se obtienen
al momento de crear el proyecto.

| Plugin                | URL                      |
|-----------------------|--------------------------|
| Spring Web            | https://start.spring.io/ |
| Spring Data JPA       | https://start.spring.io/ |
| Lombok                | https://start.spring.io/ |
| H2 Database           | https://start.spring.io/ |
| Spring Doc (opcional) | https://springdoc.org/#getting-started |

## Accesos

Acceso a la base de datos H2 mediante URL:

```sh
url: http://localhost:8080/reto/h2-console/
user: sa
password: 
```
![h2db.png](h2db.png)

Acceso a la UI de la documentación de la API mediante URL:
```sh
url: http://localhost:8080/reto/swagger-ui/index.html
```
![img_1.png](img_1.png)

## Uso en Postman


### POST (Login)
```sh
http://localhost:8080/reto/auth/log-in
```
Hay que insertar un JSON para esta petición.
Usuario de prueba admin:
``` json
{
  "username": "admin@reto.com",
  "password": "1234"
}
```

### GET
```sh
http://localhost:8080/reto/api/v1/user
```

### POST (crear User)
```sh
http://localhost:8080/reto/api/v1/user
```
Hay que insertar un JSON para esta petición.

``` json
{
	"name": "Frank Abad",
	"email": "frank@abad.com",
	"password": "qwerty2024",
	"phones": [{
        "number": "1234567",
		"citycode": "1",
		"contrycode": "57"
    }]
}
```
Probamos un Login con el usuario creado.
```sh
http://localhost:8080/reto/auth/log-in
```
Usuario de prueba recien creado:
``` json
{
  "username": "frank@abad.com",
  "password": "qwerty2024"
}
```
En la respuesta podemos ver el token generado
``` json
{
"username": "frank@abad.com",
"message": "User loged succesfully",
"status": true,
"jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmFua0BhYmFkLmNvbSIsIm5iZiI6MTczNTg4MTQ1NCwiaXNzIjoiQVVUSDBKV1QtQkFDS0VORCIsImV4cCI6MTczNTg4MzI1NCwiaWF0IjoxNzM1ODgxNDU0LCJhdXRob3JpdGllcyI6IkNSRUFURV9VU0VSLElOVkFMSURfVVNFUixSRUFEX1VTRVIsUk9MRV9BRE1JTiIsImp0aSI6IjRmMzYzZDQwLTYzY2UtNDU4YS1iMWQwLWE1NDNjN2RiMWNiYyJ9.1s11mq5TlnyR6m7Xba3X9tRuX0K8lTGQoPQ6-vnhHR8"
}
```

Hacemos una consulta por email
### GET
```sh
http://localhost:8080/reto/api/v1/user/frank@abad.com
```
Obtenemos como salida los datos del usuario donde se ve el ultimo token y la ultima fecha de login:
```sh
{
"uuid": "cf7e6412-e173-4505-a775-358406839d6b",
"name": "Frank Abad",
"email": "frank@abad.com",
"password": "$2a$10$Gomyez47bqnLJsMkSgnYTeE7ut0jWu7idvsIn44.o0ade2OZYdUIq",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
],
"created": "2025-01-03T00:15:46.987493",
"modified": "2025-01-03T00:17:34.746754",
"lastLogin": "2025-01-03T00:17:34.746754",
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmFua0BhYmFkLmNvbSIsIm5iZiI6MTczNTg4MTQ1NCwiaXNzIjoiQVVUSDBKV1QtQkFDS0VORCIsImV4cCI6MTczNTg4MzI1NCwiaWF0IjoxNzM1ODgxNDU0LCJhdXRob3JpdGllcyI6IkNSRUFURV9VU0VSLElOVkFMSURfVVNFUixSRUFEX1VTRVIsUk9MRV9BRE1JTiIsImp0aSI6IjRmMzYzZDQwLTYzY2UtNDU4YS1iMWQwLWE1NDNjN2RiMWNiYyJ9.1s11mq5TlnyR6m7Xba3X9tRuX0K8lTGQoPQ6-vnhHR8",
"active": true
}
```
En el proyecto encontraras la coleccion de Postman retoSpring.postman_collection.json


Añadir SpringDoc: https://springdoc.org/#getting-started

Creador: Frank Abad (repositorio: https://github.com/FrankAbad21/retospring)
