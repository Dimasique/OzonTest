package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;

public class TestChangeCity {
    private ChromeDriver driver;
    private WebDriverWait waiting;

    private final By CITY_BTN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button");
    private final By INPUT_CITY = By.xpath("//*[@id=\"__ozon\"]/div/div[2]/div/div/div/div/div/label/div/input");
    private final By CITY_NAME = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button/span");

    private final By MAIN_PAGE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[4]/div/div[1]/div[3]/section[1]/ul/li[1]/a");
    private final By OPEN_MAP = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[4]/div/div[2]/div[5]/div[2]/div/div[2]/div[1]/a/div/div");
    private final By ADDRESS = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[3]/div/div[2]/div[2]/div/div[2]/div/span/span");

    public TestChangeCity() {
        System.setProperty("webdriver.chrome.driver","C:\\Applications\\Chromium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().window().maximize();

        waiting = new WebDriverWait (driver, 20);
    }


    public void changeCity(String cityName) {
        driver.findElement(CITY_BTN).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(INPUT_CITY));

        driver.findElement(INPUT_CITY).sendKeys(cityName);

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.partialLinkText(cityName)).click();
    }

    public String getCityText() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(CITY_NAME).getText();
    }

    public String openMapAndCheck() {
        waiting.until(ExpectedConditions.presenceOfElementLocated(MAIN_PAGE));
        driver.findElement(MAIN_PAGE).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(OPEN_MAP));
        driver.findElement(OPEN_MAP).click();

        ChromeDriver driverNew = new ChromeDriver();
        driverNew.get("https://www.ozon.ru/geo/volsk/");

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        String text = driverNew.findElement(ADDRESS).getText();
        driverNew.quit();
        return text;
    }

    public ChromeDriver getWebDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return waiting;
    }

    public void close() {
        driver.quit();
    }
}
