# Spring boot3 OAuth2 GitHub Authorization Server for Social Login.
A sample project that shows how to implement OAuth2 login using GitHub as a resource server

### Application diagram
<p align="center"><img src="https://github.com/isalonisharma/Spring-Boot-Security/blob/main/spring-security-oauth2-github/diagram.png" width="500"></p>

### OAuth2.0 flow (How things work in action)
<p align="center"><img src="https://github.com/isalonisharma/Spring-Boot-Security/blob/main/spring-security-oauth2-github/oauth2-flow.png" width="500"></p>

### Steps to create a GitHub application
* Go to [GitHub developer portal](https://github.com/settings/developers)
* Create a new application and provide the required information
    * Set the homepage URL to http://localhost:8080
    * Authorization callback URL to http://localhost:8080/login/oauth2/code/github.

### Update the `application.yml` file
After creating a new application, you will have a client ID and a client secret. Copy these two pieces of information and paste them into the `application.yml` file

```
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            clientId: github-app-client-id-here
            clientSecret: github-app-client-secret-here
```

### Start the application and enjoy your GitHub login
