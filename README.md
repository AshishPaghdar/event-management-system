   # Event-Management-System
This is an event management system developed using Spring Boot framework. This project is managing events such as creating events and updating event. These technologies have been implemented in this project.

    1. Spring-security(JWT),
    2. Redis, 
    3. Flyway for the database migration,
    4. Global Exception, 
    5. jUnit with Jacoco (Java Code Coverage)
    6. Swagger Explorer

# There are the following steps for the setup of the project.
    1. JDK 17 
    2. Spring-boot 3.2.4
    3. Redis server v=7.2.5 for Caching
    4. Mysql server 8.3
    
# Create the database in Mysql server.
  create database event_application
  Set the databse configuration in the properties file. Like Username, Password, URL, etc..
 
 # Start the Redis-server.
 ```sh
sudo service redis-server start
```
 # Start the event management system.
 ```sh
mvn spring-boot:run
```

> Note: `We have put(Event-Managemnt-app.postman_collection.json) the collection of APIs. You can import the APIs collection into postman`

#### 1. User registration API for the event.

 ```sh
 curl --location 'http://localhost:8081/api/v1/auth/registration' \
--header 'Accept-Language: hi' \
--header 'Content-Type: application/json' \
--header 'Cookie: COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US' \
--data-raw '{
  "name": "Ashish Paghdar -1",
  "password": "password123",
  "gender": "Male",
  "address": "Krupa Apartment, Vasna, Ahmedabad",
  "email": "ashish1@example.com",
  "mobile": "9726222908",
  "eventsOfInterest": "Sports, Music, Technology"
}'
```

#### 2. User Login API for the User 
```sh
curl --location 'http://localhost:8081/api/v1/auth/login' \
--header 'Accept-Language: hi' \
--header 'Content-Type: application/json' \
--header 'Cookie: COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US' \
--data-raw '{
  "password": "password123",
  "email": "ashish1@example.com",
  "id" : "1",
  "role" : "USER"
}'
```
you will get the JWT token for the event app. 
