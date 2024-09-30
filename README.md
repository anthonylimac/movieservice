Movie Award Project
This project is a Java Spring application that manages movie awards data. It can be run using an embedded Tomcat server or as a Docker container.

Prerequisites
Before you begin, ensure you have the following installed on your machine:

Java JDK 17 or higher
Maven
TomCat

Docker (if you wish to run the application in a Docker container)
Build the Project:
    Navigate to the root directory via cmd of the project and run mvn clean install.
    After running this command, the JAR file will be generated in the target directory of your project. It will be named something like movie-award-0.0.1-SNAPSHOT.jar.
    Copy the JAR file from your project's target directory to the webapps directory of your Tomcat installation.
    navigate to the bin directory of your Tomcat installation and run catalina.bat start
    Access the Application: Open your web browser and go to:


Running with Docker
    Build the Docker Image: From the root directory of your project, build the Docker image using the following command:
    docker pull anthonylimac/movie-award
    Run the Docker container with the following command: docker run -p 8080:8080 anthonylimac/movie-award
    Access the Application: Open your web browser and go to:


Running Integration Tests
    To run the integration tests, execute the following command from the root directory of your project: mvn test
This command will run all tests defined in the project, including integration tests.

ENDPOINTS:
Get a list of all movies:
method: GET /api/v1/movies

Add a movie:
method: POST /api/v1/movies
JSON format:
{
        "producers": ["producer1", "producer2"],        
        "studios": [
            "studio1",
            "studio2"
        ],
        "title": "movie title",
        "winner": boolean,
        "nominatedYear": 2007
    }

Edit a movie:
method PUT /api/movies/edit
JSON format:
{
        "id": movieId
        "producers": ["producer1", "producer2"],        
        "studios": [
            "studio1",
            "studio2"
        ],
        "title": "movie title",
        "winner": boolean,
        "nominatedYear": 2007
    }


Delete a movie:
method: DELETE /api/movies/delete/{id}


Retrieve producers that won a prize and respective gaps between prizes:
method: GET /api/v1/gaps



    
