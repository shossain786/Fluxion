package com.fluxion.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class LocatorReader {

    private Map<String, Object> locators;

    // Constructor to load locators from a YAML file based on product name
    public LocatorReader(String productName) {
        String filePath = "locators/" + productName + "_locators.yaml"; // Construct file path dynamically
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            locators = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get locator by element name (e.g., username, password, loginButton)
    public String getLocator(String elementName) {
        Map<String, String> page = (Map<String, String>) locators.get("LoginPage");
        return page != null ? page.get(elementName) : null;
    }

    public static void main(String[] args) {
        // Example usage: Load locators for Product 1
        LocatorReader locatorReader = new LocatorReader("product1");

        // Get locators for username, password, and loginButton from Product 1
        String usernameLocator = locatorReader.getLocator("username");
        String passwordLocator = locatorReader.getLocator("password");
        String loginButtonLocator = locatorReader.getLocator("loginButton");

        System.out.println("Username Locator: " + usernameLocator);
        System.out.println("Password Locator: " + passwordLocator);
        System.out.println("Login Button Locator: " + loginButtonLocator);

        // Example usage: Load locators for Product 2
        locatorReader = new LocatorReader("product2");

        // Get locators for username, password, and loginButton from Product 2
        usernameLocator = locatorReader.getLocator("username");
        passwordLocator = locatorReader.getLocator("password");
        loginButtonLocator = locatorReader.getLocator("loginButton");

        System.out.println("Username Locator for Product 2: " + usernameLocator);
        System.out.println("Password Locator for Product 2: " + passwordLocator);
        System.out.println("Login Button Locator for Product 2: " + loginButtonLocator);
    }
}
