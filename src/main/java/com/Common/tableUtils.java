package com.Common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class tableUtils {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public tableUtils(WebDriver driver) {
        tableUtils.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    // Validate Table Value by Position method
    public void validateTableValueByPosition(By booknametablelocatorcss, int expectedRow, int expectedCol, String expectedValue) {
        String actualValue = getTableCellByPosition(booknametablelocatorcss, expectedRow, expectedCol);
        assertEqualStrings(actualValue, expectedValue);
    }

    // Get cell by position
    private String getTableCellByPosition(By booknametablelocatorcss, int row, int col) {
        WebElement tableCell = getTableCell(booknametablelocatorcss, row, col);
        return tableCell.getText();
    }

    // Get Table Cell
    private WebElement getTableCell(By booknametablelocatorcss, int row, int col) {
        // Use the locator as the base CSS selector
        String baseSelector = booknametablelocatorcss.toString().substring("By.cssSelector: ".length());
        // Append the specific row and column indexes to the CSS selector
        String cssSelector = baseSelector + " tbody tr:nth-child(" + row + ") td:nth-child(" + col + ")";
        // Wait until the cell is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    // Should Be Equal As Strings
    public void assertEqualStrings(String actualValue, String expectedValue) {
        if (!actualValue.equals(expectedValue)) {
            throw new AssertionError("Values are not equal. Actual: " + actualValue + ", Expected: " + expectedValue);
        }
    }

    public int getRowIndexByHeaderAndValue(String header, String value, String booknametablelocatorxpath) {
        int colIndex = getColumnIndexByHeader(header, booknametablelocatorxpath);
    
        List<WebElement> rows = driver.findElements(By.xpath(booknametablelocatorxpath + "//tr"));
    
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
            String cellXpath = booknametablelocatorxpath + "//tr[" + (rowIndex + 1) + "]/td[" + (colIndex + 1) + "] | //tr[" + (rowIndex + 1) + "]/th[" + (colIndex + 1) + "]";
            WebElement cell = driver.findElement(By.xpath(cellXpath));
    
            if (cell.getText().equals(value)) {
                return rowIndex;
            }
        }
    
        return -1;
    }

    public int getColumnIndexByHeader(String header, String booknametablelocatorxpath) {
        // Find the header row
        WebElement headerRow = driver.findElement(By.xpath(booknametablelocatorxpath + "//tr[1]"));

        // Find all cells in the header row
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));

        // Go through header cells to find the matching header
        for (int colIndex = 0; colIndex < headerCells.size(); colIndex++) {
            if (headerCells.get(colIndex).getText().equals(header)) {
                return colIndex;
            }
        }

        // Return -1 if not found
        return -1;
    }

    public void selectPageByNumber(String tableLocator, int pageNumber) {
        String pageLocator = String.format("//*[@id='%s']/li[%d]/a", tableLocator, pageNumber);
        WebElement pageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageLocator)));

        if (pageElement.isDisplayed()) {
            pageElement.click();
        } else {
            throw new RuntimeException("Page " + pageNumber + " not found in the pagination table");
        }
    }

    public void toggleCheckboxBasedOnValueInTable(String tableId, String valueToFind, int tdPosition) {
        String checkboxLocator = String.format("//*[@id='%s']/tbody/tr[td[contains(text(),'%s')]]/td[%d]/input", tableId, valueToFind, tdPosition);
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement checkboxElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkboxLocator)));

        if (checkboxElement.isDisplayed()) {
            checkboxElement.click();
            System.out.println("Checked checkbox for: " + valueToFind);
        } else {
            throw new RuntimeException("Could not locate the checkbox");
        }
    }

    public String getTableCellValue(int rowIndex, int colIndex, String booknametablelocatorxpath) {
        int adjustedRowIndex = rowIndex + 1;
        int adjustedColIndex = colIndex + 1;

        // Separate XPath for header and data rows
        String headerXPath = booknametablelocatorxpath + "/tr[1]/th[" + adjustedColIndex + "]";
        String dataXPath = booknametablelocatorxpath + "/tr[" + adjustedRowIndex + "]/td[" + adjustedColIndex + "]";

        // Determine whether to get header or data value
        if (rowIndex == -1) {
            return driver.findElement(By.xpath(headerXPath)).getText();
        } else {
            return driver.findElement(By.xpath(dataXPath)).getText();
        }
    }
}