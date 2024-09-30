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
