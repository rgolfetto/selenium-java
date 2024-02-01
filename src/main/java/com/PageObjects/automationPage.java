package com.PageObjects;

import com.Common.listUtils;
import com.Common.seleniumCommon;
import com.Common.tableUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class automationPage {

    private seleniumCommon seleniumCommon;
    private listUtils listUtils;
    private tableUtils tableUtils;
    private WebDriver driver;

    // Test URL
    private static final String urlLocator = "https://testautomationpractice.blogspot.com";

    // Locators

    private static final By nameLocator = By.id("name");
    private static final By emailLocator = By.id("email");
    private static final By phoneLocator = By.id("phone");
    private static final By textareaLocator = By.id("textarea");

    private static By radioButton(String groupName, String option) {
        return By.xpath(String.format("//input[@name='%s' and @id='%s']", groupName, option));
    }

    private static final By countryDropdown = By.id("country");

    private static By dayCheckbox(int position) {
        return By.cssSelector(".post-body.entry-content > .form-group:nth-of-type(4) label ~ div input:nth-of-type(" + position + ")");
    }

    private String daysListLocator(String id) {
        return ".post-body.entry-content > .form-group:nth-of-type(4) label ~ div input#" + id;
    }

    private static final By colorsList = By.id("colors");

    private static final By booknameTableLocatorCss = By.cssSelector("#HTML1 > div.widget-content > table");

    private static final String booknameTableLocatorXpath = "//*[@id='HTML1']/div[1]/table/tbody";

    private static final String paginationTableXpath = "pagination";

    private static final String paginationTableCheckbox = "productTable";

    private static final By submitButtonLocator = By.id("FSsubmit");

    private static final By frameLocator = By.cssSelector(".widget-content iframe#frame-one796456169");

    //Locators end

    public automationPage(WebDriver driver) {
        this.driver = driver;
        this.seleniumCommon = new seleniumCommon(driver);
        this.listUtils = new listUtils(driver);
        this.tableUtils = new tableUtils(driver);
    }

    public void navigateTo() {
        seleniumCommon.navigateTo(urlLocator);
    }

    public void maximizeWindow() {
        seleniumCommon.maximizeWindow();
    }
    
    public void enterName(String name) {
        seleniumCommon.enterText(name, nameLocator);
    }

    public void enterEmail(String email) {
        seleniumCommon.enterText(email, emailLocator);
    }

    public void enterPhone(String phone) {
        seleniumCommon.enterText(phone, phoneLocator);
    }

    public void enterTextarea(String textarea) {
        seleniumCommon.enterText(textarea, textareaLocator);
    }

    public void clickRadioButton(String groupName, String option) {
        seleniumCommon.clickElement(radioButton(groupName, option));
    }

    public void selectCountry(String value) {
        seleniumCommon.selectDropdown(countryDropdown, value);
    }

    public void selectDayByPosition(int position) {
        listUtils.selectElementByPosition(dayCheckbox(position), position);
    }
    
    public void selectDayByName(String id) {
        String lowercaseId = id.toLowerCase();
        String daysListLocator = daysListLocator(lowercaseId);
        listUtils.selectElementByValue(daysListLocator);
    }

    public void selectColor(String value) {
        listUtils.selectValueFromList(colorsList, value);
    }

    public void validateTableValueByPosition(int expectedRow, int expectedCol, String expectedValue) {
        tableUtils.validateTableValueByPosition(booknameTableLocatorCss, expectedRow, expectedCol, expectedValue);
    }

    public void validateTableValueByHeaderAndExpectedValue(String header, String expectedValue) {
        tableUtils tableUtils = new tableUtils(driver);
        int colIndex = tableUtils.getColumnIndexByHeader(header, booknameTableLocatorXpath);
        int rowIndex = tableUtils.getRowIndexByHeaderAndValue(header, expectedValue, booknameTableLocatorXpath);

        // Get the actual value
        String actualValue = tableUtils.getTableCellValue(rowIndex, colIndex, booknameTableLocatorXpath);

        System.out.println("Expected Value: " + expectedValue);
        System.out.println("Actual Value: " + actualValue);

        tableUtils.assertEqualStrings(actualValue, expectedValue);
    }
    

    public void selectPageByPageNumber(int pageNumber) {
        tableUtils.selectPageByNumber(paginationTableXpath, pageNumber);
    }

    public void markCheckboxBasedOnValueInTable(String value, int col) {
        tableUtils.toggleCheckboxBasedOnValueInTable(paginationTableCheckbox, value, col);
    }

    public void clickSubmitButton() {
        // Switch to the frame
        seleniumCommon.selectFrame(frameLocator);
    
        // Click the submit button
        seleniumCommon.clickOnButton(submitButtonLocator);
    
        // Switch back to the default content
        seleniumCommon.unselectFrame();
    }
    
}