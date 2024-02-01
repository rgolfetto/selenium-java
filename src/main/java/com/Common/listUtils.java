package com.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class listUtils {

    private WebDriver driver;

    public listUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void selectElementByPosition(By locator, int position) {
        List<WebElement> elements = driver.findElements(locator);

        elements.get(position - 1).click();
    }

    public void selectElementByValue(String locator) {
        WebElement element = driver.findElement(By.cssSelector(locator));
        element.click();
    }

    public void selectValueFromList(By locator, String value) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }

}
