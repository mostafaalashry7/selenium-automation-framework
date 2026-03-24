# 🚀 Selenium UI Automation Framework

## 🌟 Overview

A robust, scalable, and production-ready **UI Automation Testing Framework** built using modern automation best practices.

This project demonstrates how to design maintainable, reusable, and stable UI tests using a layered automation architecture similar to real-world enterprise frameworks.

The framework focuses on clean separation of concerns, test stability, cross-browser support, and execution scalability.

---

## 🛠️ Tech Stack

* ☕ Java
* 🌐 Selenium WebDriver
* 🧪 TestNG
* 📦 Maven
* 📊 Allure Reports

---

## 🧱 Architecture Design

The framework is designed using a structured approach to keep responsibilities isolated and the codebase easy to maintain.

### 🔹 Test Base

Handles the test lifecycle and shared initialization logic.

**Responsibilities:**
- Browser setup and teardown
- Test data loading
- Page objects initialization
- Framework initialization
- Navigation to application URL

---

### 🔹 Framework Layer

Contains the reusable browser interaction logic and low-level automation utilities.

**Responsibilities:**
- Click handling with fallback strategies
- Smart waits and synchronization
- Scrolling utilities
- Alert handling
- Navigation helpers
- JavaScript-based interaction handling

This layer is designed to improve stability and reduce flaky behavior in dynamic UI scenarios.

---

### 🔹 Page Layer (POM)

Implements the **Page Object Model** by encapsulating locators, actions, and assertions for each page.

**Pages included:**
- Home Page
- Cart Page
- Products Page
- Product Details Page
- Checkout Page
- Payment Page
- Signup Page
- Login Page
- Contact Us Page

Each page class is responsible only for the behavior of its own screen.

---

### 🔹 Flow Layer

Contains reusable business flows that combine actions from multiple pages.

**Examples:**
- Full signup flow
- Full sign in flow
- Checkout and payment flow

This layer reduces duplication and keeps test methods clean and readable.

---

### 🔹 Data Layer

Uses JSON-based test data mapped into Java classes.

**Benefits:**
- Clean separation between data and test logic
- Easy maintenance
- Reusability across scenarios

---

### 🔹 Test Layer

Contains well-structured TestNG test classes.

**Responsibilities:**
- Define test scenarios
- Perform assertions
- Use page methods and flows instead of direct Selenium calls

This keeps tests focused on **what is being tested**, while implementation details stay inside the framework.

---

## 📁 Project Structure

```text
automationProject
├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.ui.automation.framework
│   │   │       ├── data
│   │   │       │   ├── contactUsData.java
│   │   │       │   ├── invalidLoginData.java
│   │   │       │   ├── paymentData.java
│   │   │       │   ├── productDetailsData.java
│   │   │       │   ├── reviewData.java
│   │   │       │   └── userData.java
│   │   │       ├── flows
│   │   │       │   └── flow.java
│   │   │       ├── pages
│   │   │       │   ├── cartPage.java
│   │   │       │   ├── CheckOutPage.java
│   │   │       │   ├── contactUsPage.java
│   │   │       │   ├── getStartedPage.java
│   │   │       │   ├── homePage.java
│   │   │       │   ├── paymentPage.java
│   │   │       │   ├── productDetailsPage.java
│   │   │       │   ├── productsPage.java
│   │   │       │   └── signUpPage.java
│   │   │       └── utils
│   │   │           ├── framework.java
│   │   │           └── readJson.java
│   │   └── resources
│   │       ├── contactUsData.json
│   │       ├── invalidLoginData.json
│   │       ├── paymentData.json
│   │       ├── productDetailsData.json
│   │       ├── reviewData.json
│   │       └── userData.json
│   └── test
│       ├── java
│       │   └── com.ui.automation.framework
│       │       ├── base
│       │       │   └── BaseTest.java
│       │       ├── listeners
│       │       │   └── TestListener.java
│       │       └── tests
│       │           ├── AuthenticationTests.java
│       │           ├── CartManagementTests.java
│       │           ├── CheckoutPlacementTests.java
│       │           ├── CheckoutValidationTests.java
│       │           ├── ContactAndReviewTests.java
│       │           ├── HomePageUiTests.java
│       │           ├── ProductCatalogTests.java
│       │           └── SubscriptionTests.java
│       └── resources
│           └── firstphoto.png
├── .gitignore
├── pom.xml
├── testng-chrome.xml
└── testng-firefox.xml
```

---

## ✨ Key Features

✔ Clean layered architecture  
✔ Page Object Model (POM)  
✔ Reusable business flows  
✔ Cross-browser execution  
✔ Parallel execution using TestNG  
✔ Thread-safe WebDriver handling  
✔ Smart waits and flaky test handling  
✔ JavaScript-based interaction handling  
✔ File upload support  
✔ Allure reporting integration

---

## ⚡ Parallel Execution

```xml
<suite parallel="classes" thread-count="3">
</suite>
```

---

## 🌐 Cross Browser Support

The framework supports:

- Chrome
- Firefox

Browser selection is controlled through TestNG XML parameters.

---

## ▶️ How to Run

Run the framework using TestNG suite files:

- `testng-chrome.xml`
- `testng-firefox.xml`

---

## 📊 Allure Reporting

```bash
allure serve allure-results
```

---

## 🎯 Design Decisions

- The **Page Layer** isolates UI logic from tests
- The **Flow Layer** reduces duplication and improves readability
- The **Framework Layer** centralizes reusable browser actions
- Thread-safe driver handling supports parallel execution
- JavaScript fallback handling improves stability with dynamic UI


---

## ⭐ Final Note

This project reflects a practical real-world approach to building a stable and maintainable UI automation framework with a strong focus on clean design, reusability, and execution reliability.