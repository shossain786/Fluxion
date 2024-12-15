# Fluxion Framework

Fluxion is a robust and flexible automation framework designed to streamline end-to-end test automation. It supports seamless integration with various automation tools and can be configured as a dependency for other test projects. With a focus on user experience and simplicity, Fluxion aims to redefine test automation practices.

## Features

- **Multi-Browser Support**: Easily configurable for Chrome, Firefox, Edge, and other browsers.
- **Dynamic Driver Management**: Leverages DriverManager for efficient WebDriver handling.
- **Configuration Handling**: Centralized configuration management using YAML.
- **Logging**: Integrated Log4j2 for detailed test execution logs.
- **BDD Support**: Compatible with Cucumber for Behavior-Driven Development.
- **Utility Tools**: Includes reusable utilities like FluentWait and ElementHandler.
- **Extendable**: Can be added as a dependency for test projects to provide automation solutions.

## **Project Structure**

The project is structured as follows:

```
fluxion
├── config
|   |──application.yaml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.fluxion
|   |   |         └──  config
|   |   |         |     ├──ConfigLoader       # Loads configurations dynamically
|   |   |         └── exceptions
|   |   |         └── helpers
|   |   |         └── locators
|   |   |         └── reports
|   |   |         
│   │   ├── resources
│   │       ├── config
│   │       │   └── application.yaml      # Configuration file
│   │       └── logging
│   │           └── log4j2.xml            # Log4j2 configuration
│   └── test
│       ├── java
│             └── com.fluxion
│                 |__core
|                    └── BaseTest.java          # Handles test setup and teardown
│                    ├── DriverManager.java     # Manages WebDriver instances
│                    ├── SeleniumActions.java   # Manages Selenium Actions
│       ├── resources
│           └── testdata
│               └── test-config.yaml       # Example test data file
├── pom.xml                                # Maven project file
├── README.md                              # Project documentation
└── LICENSE                                # Project license

```

---

## Getting Started

### Prerequisites

Ensure the following are installed:
- Java 8 or higher
- Maven 3.6+
- IDE of your choice (e.g., IntelliJ IDEA, Eclipse)



## **Installation and Usage**

### **Using Fluxion as a Dependency**
#### 1. Add to Maven Project
Fluxion can be hosted on **Maven Central** or **JitPack**. Add the following to your project's `pom.xml`:

[![](https://jitpack.io/v/shossain786/fluxion.svg)](https://jitpack.io/#shossain786/fluxion)

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.shossain786</groupId>
        <artifactId>fluxion-framework</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

#### 2. Add as a JAR File
1. Build the Fluxion project:
   ```bash
   mvn clean install
   ```
2. Copy the generated `fluxion-framework-1.0.0.jar` (from `target` folder) into the `libs` folder of your project.
3. Add it to your classpath.

---

## **Configuration**

All configurations for Fluxion are managed through the `application.yaml` file.

### **Example `application.yaml`**
```yaml
browser: chrome
headless: false
baseUrl: https://example.com
timeouts:
  implicitWait: 10
  explicitWait: 20
  pageLoadTimeout: 30
logging:
  level: info
```

---

## **Usage**

Here’s how to use the Fluxion framework in your test automation projects:

### **1. Setup Driver and Configurations**
```java
import com.fluxion.core.BaseTest;
import com.fluxion.core.DriverManager;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {
    @Test
    public void testExample() {
        DriverManager.getDriver().get("https://example.com");
        System.out.println("Test executed successfully!");
    }
}
```

### **2. Use Configurations Dynamically**

```java
import com.fluxion.config.ConfigLoader;

public class SampleConfigUsage {
   public void printBaseUrl() {
      String baseUrl = ConfigLoader.getProperty("baseUrl");
      System.out.println("Base URL: " + baseUrl);
   }
}
```

### **3. Element Handling**
```java
import com.fluxion.core.ElementHandler;
import org.openqa.selenium.By;

public class ElementUsage {
    public void interactWithElement() {
        ElementHandler.click(By.id("submit-button"));
    }
}
```
3. For BDD, write feature files in `src/test/resources/features` and step definitions in `com.fluxion.stepdefinitions`.

---

## **Build the Project**

To package the Fluxion framework as a JAR:
```bash
mvn clean install
```

---

## **Contributing**

We welcome contributions to enhance the Fluxion framework. To contribute:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

## **License**

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## **Contact**

For questions, feedback, or feature requests, reach out to **[sddmhossain786@gmail.com]** or create an issue in this repository.
