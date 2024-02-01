// SeleniumCommon.java
package com.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class seleniumCommon {

    private static WebDriver driver;

    public seleniumCommon(WebDriver driver) {
        seleniumCommon.driver = driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void enterText(String text, By locator) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void selectDropdown(By locator, String value) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }

    public void clickOnButton(By submitButtonLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
        buttonElement.click();
    }

    public void selectFrame(By frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(frameLocator));
        driver.switchTo().frame(frameElement);
    }
    
    public void unselectFrame() {
        driver.switchTo().defaultContent();
    }
}
