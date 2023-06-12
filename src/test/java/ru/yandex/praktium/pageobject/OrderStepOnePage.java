package ru.yandex.praktium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStepOnePage {
    private final WebDriver driver;
    private final By firstNameInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    private final By secondNameInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    private final By addressInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    private final By subwayInput = By.xpath("//input[@class='select-search__input']");
    private final By subwaySelect = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]/button");
    private final By phoneInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    private final By proceedToStepTwoButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    public OrderStepOnePage(WebDriver driver) {
        this.driver = driver;
    }
    public void proceedToStepTwo () {
        driver.findElement(proceedToStepTwoButton).click();
    }

    private void setSubway(String subway) {
        driver.findElement(subwayInput).click();
        driver.findElement(subwayInput).sendKeys(subway);
        driver.findElement(subwaySelect).click();
    }

    public void completeForm (String firstName, String lastName, String address, String subway, String phone) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(secondNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        setSubway(subway);
        driver.findElement(phoneInput).sendKeys(phone);
        proceedToStepTwo();
    }
}
