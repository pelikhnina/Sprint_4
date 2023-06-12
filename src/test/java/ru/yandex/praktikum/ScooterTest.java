package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktium.pageobject.ScooterPage;

@RunWith(Parameterized.class)
public class ScooterTest {
    private WebDriver driver;
    private final String locatorId;
    private final String locatorPath;
    private final String expected;

    public ScooterTest(String locatorId, String locatorPath, String expected) {
        this.locatorId = locatorId;
        this.locatorPath = locatorPath;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][] {
                {
                    "accordion__heading-0", "//*[@id=\"accordion__panel-0\"]/p",
                    "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                    "accordion__heading-2", "//*[@id=\"accordion__panel-2\"]/p",
                    "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                }
        };
    }

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nina\\WebDriver\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkScooter() {
        ScooterPage scooterPage = new ScooterPage(driver);
        scooterPage.openAccordionItem(locatorId);
        String actualLocatorText = scooterPage.getLocatorText(locatorPath);
        Assert.assertEquals(expected, actualLocatorText);
    }
    @After
    public void end() {
        driver.quit();
    }
}
