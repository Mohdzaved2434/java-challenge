### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### What I did
- Add tests
  
- Change syntax
- Protect controller end points
   >>Implemented it using spring security with JWT
- Add caching logic for database calls
   >> Implemented it using EH cache
- Improve doc and comments
   >> Added swagger properly and comments
- Fix any bug you might find
   >> Fixed service issues in get, delete, update api and handled the exceptions

  
- Added Spring Global Exception handler and custom application exception (exception/*)
- Used constants and api response codes (constants/*)
- Created standard api response  (helper/ResponseProvider)
- Separated models for controller and entity for DAO (model/* , entity/*)
- Implemented swagger properly

#### What I would have done if I had more time
- Would have configured role based spring security with database and created one more Endpoint for user registration 
- Would have Implemented EH cache for getEmployees (all employee)    
- Would have Implemented logging using spring AOP
- Would have implemented spring actuator for checking health and monitoring
- Would have implemented feature tests (integration) and unit tests

#### How to access Apis
- first login with credentials (username: axa_user, password: password) and get the token
   - URL: http://localhost:8080/api/v1/login
   - Request body 
    >> {
     "username": "axa_user",
     "password": "password"
     }
  - Response Body 
    >> {
    "status": "OK",
    "code": "SUCCESS",
    "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFfdXNlciIsImV4cCI6MTY0ODI5ODA1NCwiaWF0IjoxNjQ4MjgwMDU0fQ.cX_leHVkwBP7PkHH-s747n3Itn0BbCRo3f-AIOoMqBs7YPTwdOB6Bio76R376YAtUUsUdaW2gTKbDrkJt3UVqg"
    }
    }
- Add token in the Authorization header for accessing all APIs as following
    >> Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFfdXNlciIsImV4cCI6MTY0ODI5ODA1NCwiaWF0IjoxNjQ4MjgwMDU0fQ.cX_leHVkwBP7PkHH-s747n3Itn0BbCRo3f-AIOoMqBs7YPTwdOB6Bio76R376YAtUUsUdaW2gTKbDrkJt3UVqg

#### Your experience in Java
- I have around 6 years of experience in Java/J2EE technologies like
    >> Spring framework (Spring boot, Spring Transaction, Spring AOP, Spring Security, Spring MVC),
     Hibernate, REST, SOAP webservices, JDBC, JSP, Servlet  
- I have been using spring boot for last 4 years
