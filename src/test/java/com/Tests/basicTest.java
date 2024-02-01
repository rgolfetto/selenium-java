package com.Tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.PageObjects.automationPage;

public class basicTest {

    @Test
    public void performBasicTest() throws InterruptedException {
        //Chomedriver location
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        // Option to use chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Create an instance of AutomationPage
        automationPage automationPage = new automationPage(driver);

        // Use methods from AutomationPage
        automationPage.navigateTo();
        automationPage.maximizeWindow();

        // Enter information separately
        automationPage.enterName("Jarl Balgruf");
        automationPage.enterEmail("dragonslayer@skyrim.com");
        automationPage.enterPhone("19234568");
        automationPage.enterTextarea("Whiterun St, 30, 250100");

        // Select gender
        automationPage.clickRadioButton("gender", "female");

        // Select a country from the dropdown
        automationPage.selectCountry("United States");

        //Select Day from Days List By Position
        automationPage.selectDayByPosition(1);
        
        //Select Day from Days List By Name 
        automationPage.selectDayByName("Wednesday");

        //Select color
        automationPage.selectColor("Yellow");

        //Validate table value by position
        automationPage.validateTableValueByPosition(2, 3, "Selenium");
        
        //Validate table value by header and expected value
        automationPage.validateTableValueByHeaderAndExpectedValue("Subject", "Javascript");

        //Select page by page number
        automationPage.selectPageByPageNumber(3);

        //Mark checkbox by value and row
        automationPage.markCheckboxBasedOnValueInTable("Product 14", 4);

        //Click submit
        automationPage.clickSubmitButton();

        // Close the browser
        driver.quit();
    }
}
