# JWT-Authentication using SpringBoot 3.2.2
JWT stands for JSON Web Token. They are a popular way to implement authentication and authorization in web applications. 
A JWT is made up of three parts:
<br>
- `Header` : The header contains information about the token, such as the type of token (JWT) and the signing algorithm used.
- `Payload` : The payload is the main body of the token and contains the claims, which are statements about the user or other entity.
- `Signature` : The signature is used to verify the token's integrity. It is created by signing the header and payload with a secret or public/private key pair.
<br>
When a user logs in to a web application, the server generates a JWT and sends it back to the client. The client can then store the token in local storage or session storage.
When the user makes subsequent requests to the server, the client includes the JWT in the request header. The server can then verify the token and authenticate the user.
<br>

### JWT flow (How things really work in action)
<p align="center"><img src="https://github.com/isalonisharma/Spring-Boot-Security/blob/main/spring-security-jwt/diagram.png" width="500"></p>
