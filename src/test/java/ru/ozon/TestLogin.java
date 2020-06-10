package ru.ozon;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLogin {

    private ChromeDriver driver;
    private WebDriverWait waiting;

    private By BUTTON_LOGIN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/div[1]/a/span[2]");
    private By INPUT_PHONE = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div/input");
    private By GET_CODE = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button");
    private By BUCKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/div[2]/a");
    private By A = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[2]/a");

    public TestLogin() {
        System.setProperty("webdriver.chrome.driver","C:\\Applications\\Chromium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().window().maximize();

        waiting = new WebDriverWait (driver, 20);
    }

    public TestLogin(ChromeDriver driver, WebDriverWait waiting) {
        this.driver = driver;
        this.waiting = waiting;
    }

    public void openFormToInputPhone() {
        waiting.until(ExpectedConditions.presenceOfElementLocated(BUCKET));
        driver.findElement(BUCKET).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(A));
        driver.findElement(A).click();
    }

    public void inputPhone(String phone) {
        waiting.until(ExpectedConditions.presenceOfElementLocated(INPUT_PHONE));
        driver.findElement(INPUT_PHONE).sendKeys(phone);
        driver.findElement(GET_CODE).click();
    }

    String getTextAfterLogin() {
        waiting.until(ExpectedConditions.presenceOfElementLocated(BUTTON_LOGIN));
        return driver.findElement(BUTTON_LOGIN).getText();
    }

    public void close() {
        driver.quit();
    }
}
