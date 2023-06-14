package ru.yandex.praktikum;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.yandex.praktium.pageobject.OrderStepOnePage;
import ru.yandex.praktium.pageobject.OrderStepTwoPage;
import ru.yandex.praktium.pageobject.ScooterPage;

@RunWith(Parameterized.class)
public class OrderBottomButtonTest {
    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String subway;
    private final String phone;
    private final String date;
    private final String rent;

    public OrderBottomButtonTest(String firstName, String lastName, String address, String subway, String phone, String date, String rent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
    }

    @Parameterized.Parameters
    public static Object[][] getFormData() {
        return new Object[][] {
                {
                    "Вася", "Петров", "Москва", "Арбатская", "96633399343", "23.02.2023", "сутки"
                },
                {
                    "Петров", "Водкин", "Питер", "Театральная", "96633399323", "21.05.2023", "трое суток"
                }
        };
    }

    @Before
    public void start() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Nina\\WebDriver\\bin\\geckodriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nina\\WebDriver\\bin\\chromedriver.exe");

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //driver = new ChromeDriver(options);
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkOrder() {
        ScooterPage scooterPage = new ScooterPage(driver);
        scooterPage.clickOnCookieButton();
        scooterPage.startOrderBottomButton();

        OrderStepOnePage orderStepOnePage = new OrderStepOnePage(driver);
        orderStepOnePage.completeForm(firstName, lastName, address, subway, phone);

        OrderStepTwoPage orderStepTwoPage = new OrderStepTwoPage(driver);
        orderStepTwoPage.completeForm(date, rent);
        String actualSuccessText = orderStepTwoPage.getOrderSuccessText();
        Assert.assertThat(actualSuccessText, CoreMatchers.containsString("Заказ оформлен"));
    }

    @After
    public void end() {
        driver.quit();
    }
}
