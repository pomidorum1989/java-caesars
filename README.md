# Project Name

## Overview

This project is a BDD (Behavior-Driven Development) test framework using Cucumber for defining tests, Java for implementation, Allure for reporting, and TestNG for test execution.<br> 
The framework is designed to help you write readable and maintainable tests for your application.

## Project Structure

- **`src/test/java`**: Contains the step definitions and test classes.
- **`src/test/resources`**: Contains the Cucumber feature files.
- **`pom.xml`**: Maven configuration file.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/pomidorum1989/java-caesars
   mvn test -Dcucumber.filter.tags="@API or @Smoke" -Dbrowser=chrome_docker