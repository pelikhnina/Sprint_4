package ru.yandex.praktium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By startOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");
    private final By startOrderBottomButton = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");

    private final By accordion = By.xpath("//div[@class=\"accordion\"]");
    private final By accordionItem = By.xpath(".//div[@class=\"accordion__item\"]");
    private final By accordionPanel = By.xpath(".//div[@class=\"accordion__panel\"]");

    public ScooterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCookieButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }
    public String getAccordionItemText (String locatorText) {
        return driver.findElement(accordionItem).findElement(By.xpath("//div[contains(text(), '" + locatorText +"')]")).getText();
    }

    public String getAccordionPanelText (String locatorText) {
        WebElement itemPanel = driver
                .findElement(accordionItem)
                .findElement(accordionPanel)
                .findElement(By.xpath("//p[contains(text(), '" + locatorText +"')]"));
        return itemPanel.getText();
    }
    public void startOrder() {
        driver.findElement(startOrderButton).click();
    }
    public void startOrderBottomButton() {
        driver.findElement(startOrderBottomButton).click();
    }

    public void clickOnAccordionItem (String question) {
        driver.findElement(accordionItem).findElement(By.xpath("//div[contains(text(), '" + question + "')]")).click();
    }
}
