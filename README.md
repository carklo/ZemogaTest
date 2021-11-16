# Portfolio Test

## Technologies
The test was built using Java 11 over Spring boot web framework and Gradle as a dependency manager. The front end was built using Thymeleaf a modern server-side Java template engine.


## How to run the app

In a Gradle environment run the following command to start up the local server under the root of the project

```bash
./gradlew bootRun
```

## Usage
Access the Profile page on localhost:8080/?email=santidils7@gmail.com, you have to mandatory put the email request parameter due to the design of the test, the email "santidils7@gmail.com" it's the email of the only existing profile user on the portfolio table.

### Get the profile information
```curl
curl --location --request GET 'http://localhost:8080/getProfile?email=santidils7@gmail.com'
```

### Modify the profile information
```curl
curl --location --request PUT 'http://localhost:8080/modifyProfile?email=santidils7@gmail.com' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "name": "Santiago Ardila Acuña",
    "experience": "Master’s in Software Engineering and Systems and Computer Engineer from Universidad de los Andes, with experience and knowledge in: architecture, design, and software development with highly complex data management in multiple areas.",
    "imagePath": "images/about.jpg",
    "twitterUser": "elonmusk",
    "email": "santidils7@gmail.com",
    "address": "Bogota, Colombia",
    "zipCode": "7777",
    "phone": "+571234567890"
}'
```

## Stetimate time to complete
About 2 entire days.
