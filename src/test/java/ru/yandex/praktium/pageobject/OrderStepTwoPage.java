package ru.yandex.praktium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderStepTwoPage {
    private final WebDriver driver;

    private final By dateInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private final By rentDropDownRoot = By.xpath(".//div[@class='Dropdown-root']");
    private final By rentDropDownMenu = By.xpath(".//div[@class='Dropdown-menu']");
    private final By goToConfirmModalButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    private final By makeOrderButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
    private final By orderSuccessText = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");

    public OrderStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    private void gotToConfirmModal() {
        driver.findElement(goToConfirmModalButton).click();
    }

    private void makeOrder() {
        driver.findElement(makeOrderButton).click();
    }

    public String getOrderSuccessText() {
        return driver.findElement(orderSuccessText).getText();
    }

    private void setCalendarDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ENTER);
    }
    private void setRentTime(String rent) {
        driver.findElement(rentDropDownRoot).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(rentDropDownMenu));
        List<WebElement> elements = driver.findElements(rentDropDownMenu);
        for(WebElement element: elements) {
            if(element.getText().contains(rent)) {
                element.click();
            }
        }
    }

    public void completeForm (String date, String rent) {
        setCalendarDate(date);
        setRentTime(rent);
        gotToConfirmModal();
        makeOrder();
    }
}
