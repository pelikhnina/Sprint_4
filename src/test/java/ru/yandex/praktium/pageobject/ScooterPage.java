package ru.yandex.praktium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By startOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");

    public ScooterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCookieButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }
    public void clickOnLocator (String locator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id(locator))).click();
    }
    public String getLocatorText (String locatorText) {
        return driver.findElement(By.xpath(locatorText)).getText();
    }
    public void startOrder() {
        driver.findElement(startOrderButton).click();
    }
    public void openAccordionItem (String locatorId) {
        clickOnCookieButton();
        clickOnLocator(locatorId);
    }
}
