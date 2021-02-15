Coverage: 93%
# To Do List Project

This project is a simple to do list application, which users can interact with via a webpage. The goal of this project was to create a full stack OOP-based web application with basic CRUD functionality. The structure of this project resembles a three-thier architecture: the front end (the presentation layer) was built using visual studio code and written in Javascript, HTML and CSS; the back end (the business layer) was built using Spring and written in Java; and the locally hosted database (the data layer) was built using a relational databse language. An API, built in the back end using Spring, is used to act as a messenger between these layers and allow for the interaction, communication and access of data with external software components.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to run this application - view the source code and tests, as well as view the web page - you will require a Java runtime environment, as well as the Spring Boot IDE. You will also need to have a version of Maven in order to build the project.

### Installing

Steps to follow to get the application running:
1. fork this repository
2. open the folder up in the Spring IDE
3. run the Spring application
4. visit "https://localhost:8080/h2-console" to view the database
5. visit "https://localhost:8080/index.html" to view the web page
6. follow the relevant instructions on the web page to perform CRUD functionality 

## Running the Tests

To run the tests for this application, JUnit is used (as well as Selenium and Mockito). Simply open the source code in your IDE and run the tests as a JUnit application.

### Unit Tests

Unit tests have been used to test aspects of this application. Unit testing is a method by which individual units of source code are tested; this is done to validate whether each unit of code performs as expected and hence determine whether each unit can successfully be used.

### Integration Tests

Integration testing combines individual units of code and tests them as a group - it is used to test the interactions between integrated units of code (it checks that there are no faults in the communications between classes). System integration testing is used in this project; this involves the overall testing of the complete sysytem at hand and it's various individual classes/components.

### Selenium Tests

The front end development of this project was tested using the automated testing tool, selenium. Selenium is used to automate testing across a web browser; providing a framework for browser-based automation tests.

## Deployment

Deployment of this project has been managed through the use of a CI pipeline. Back end source code is written in Java, front end source code is written in Javascript, HTML and CSS, and the relational database language is written using MySQL. This code is pushed to a VCS (GitHub) and tracked and managed using a Jira Kanban Board, which uses the MoSCoW prioritisation technique. Maven is used as a build tool for this process - it's pom.xml file encompasses the various dependences required for the build. Testing was administered using JUnit and Selenium. Mockito is also used to mock interfaces and add a dummy functionality during testing. The code for this project was run through SonarQube, whereby it underwent a static analysis to identify any bugs, vulnerabilities or code smells - resulting in the relevant refactoring of such code.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - IDE used for API development
* [Visual Studio Code](https://code.visualstudio.com/) - IDE used for front end development
* [MySQL](https://www.mysql.com/) - Database Language
* [Localhost:8080](https://localhost:8080/) - Database Host
* [JUnit](https://junit.org/junit5/) - Testing
* [Mockito](https://site.mockito.org/) - Testing
* [Selenium](https://www.selenium.dev/) - Automated Testing
* [SonarQube](https://www.sonarqube.org/) - Static Analysis Tool
* [Jira](https://team-1607440641058.atlassian.net/secure/RapidBoard.jspa?projectKey=TDLP&rapidView=4) - Project Management (this is a link to my Jira kanban board for this project)
* [Postman](https://www.postman.com/) - Assisted API development

## Versioning

* [Git](https://git-scm.com/) - Version Control System (VCS)
* [Github](https://github.com/) - VCS Host

## Authors

* **Kiera Walton** - *Author* - [kierawaltonqa](https://github.com/kierawaltonqa)

## Licence

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details.
[BootStrap](https://github.com/twbs/bootstrap/blob/v4.0.0/LICENSE)


## Acknowledgements

* **Savannah Vaithilingam** - *Javascript, HTML and CSS trainer* 
* **Alan Davis** - *Spring Boot trainer*
