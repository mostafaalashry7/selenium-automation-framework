# Selenium Automation Framework

UI Test Automation Framework built using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- Allure Reports
- Page Object Model (POM)

## Project Structure

src/main/java
- pages → Page Objects
- flows → Business flows
- utils → Helper methods
- data → Test data

src/test/java
- base → Base test setup
- tests → Test cases
- listeners → TestNG Listeners

## Run Tests

Run tests using:

mvn test

## Generate Allure Report

Generate the report using:

allure serve allure-results

## Author

Mostafa Alashry
