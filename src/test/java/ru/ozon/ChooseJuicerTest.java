package ru.ozon;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseJuicerTest {

    private ChromeDriver driver;
    private WebDriverWait waiting;

    private final By CATALOG = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[2]/div/div[1]/button/div");
    private final By KITCHEN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/" +
            "div[2]/div/div[2]/div/div[1]/div/a[11]");

    private final By KITCHEN_STUFF = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/a");
    private final By SHOW_MORE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/span");
    private final By JUICERS = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/div[6]/a");
    private final By PRICE_MIN = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/" +
            "div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input");

    private final By PRICE_MAX = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/" +
            "div[1]/div/aside/div[2]/div[2]/div[2]/div[2]/input");

    private final By CHECK_PRICE_SEGMENT = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/" +
            "div[2]/div[2]/div[2]/div[2]/div/div/button/div/span");

    private final By CHECK_TITLE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[1]/div/div[2]/h1");
    private final By SORT_BY_BUTTON = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/" +
            "div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/input");

    private final By ADD_JUICER = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/" +
                    "div/div/div[1]/div/div/div[3]/div[3]/div/div/button/div");

    private final By ADD_ONE = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[1]/div[1]/div/" +
                    "div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input");

    private final By CHECK_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/a[2]");
    private final By IS_IN_BASKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/" +
            "div/div[3]/div[3]/div/div/div/div/div[1]");

    private final By CHECK_AMOUNT = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/" +
                    "div[4]/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/div");

    private final By PRICE_JUICER = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/" +
                    "div[4]/div[1]/div[1]/div/div[2]/div[3]/div[3]/div[1]/div/span");

    private final By PRICE_IN_BUCKET = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/" +
            "div[4]/div[2]/div/section/div[2]/div[4]/span[2]");

    private final By POWER = By.xpath("//*[@id=\"__ozon\"]/div/" +
            "div[1]/div[3]/div[2]/div[1]/div/aside/div[8]/div[2]/div[2]/div[1]/input");

    public ChooseJuicerTest() {
        System.setProperty("webdriver.chrome.driver","C:\\Applications\\Chromium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().window().maximize();

        waiting = new WebDriverWait (driver, 20);
    }

    public void getToJuicers() {
        driver.findElement(CATALOG).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(KITCHEN));
        driver.findElement(KITCHEN).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(KITCHEN_STUFF));
        driver.findElement(SHOW_MORE).click();

        waiting.until(ExpectedConditions.presenceOfElementLocated(JUICERS));
        driver.findElement(JUICERS).click();
    }

    public void setPrice(String low, String high) {
        waiting.until(ExpectedConditions.presenceOfElementLocated(PRICE_MIN));
        driver.findElement(PRICE_MIN).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(PRICE_MIN).sendKeys(low);
        driver.findElement(PRICE_MIN).sendKeys(Keys.ENTER);
        sleep(2000);

        driver.findElement(PRICE_MAX).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(PRICE_MAX).sendKeys(high);
        driver.findElement(PRICE_MAX).sendKeys(Keys.ENTER);
        sleep(2000);
    }

    public void setPower(String power) {
        driver.findElement(POWER).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        driver.findElement(POWER).sendKeys(power);
        driver.findElement(POWER).sendKeys(Keys.ENTER);
        sleep(2000);
    }

    public void testFilter(String text1, String text2) {
        waiting.until(ExpectedConditions.presenceOfElementLocated(CHECK_PRICE_SEGMENT));

        assert text1.equals(driver.findElement(CHECK_PRICE_SEGMENT).getText())
                && text2.equals(driver.findElement(CHECK_TITLE).getText());
    }

    public void sortAndAddCheapest() {
        driver.findElement(SORT_BY_BUTTON).click();
        sleep(2000);
        driver.findElement(SORT_BY_BUTTON).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(2000);
        driver.findElement(ADD_JUICER).click();
        sleep(1000);

        driver.findElement(CHECK_BASKET).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(IS_IN_BASKET));
    }

    public double getPrice() {
        waiting.until(ExpectedConditions.presenceOfElementLocated(PRICE_JUICER));
        String strPrice = driver.findElement(PRICE_JUICER).getText();
        strPrice = strPrice.substring(0, strPrice.length() - 2).replace(" ", "");
        return Double.parseDouble(strPrice);
    }

    public String addAndReturnAmount(int need) {
        for (int i = 1; i < need; i++) {
            driver.findElement(ADD_ONE).sendKeys(Keys.ARROW_DOWN);
        }
        driver.findElement(ADD_ONE).sendKeys(Keys.ENTER);
        return driver.findElement(CHECK_AMOUNT).getText();
    }

    public double getTotalPrice() {
        sleep(5000);
        String strPrice = driver.findElement(PRICE_IN_BUCKET).getText();
        strPrice = strPrice.substring(0, strPrice.length() - 2).replace(" ", "");
        return  Double.parseDouble(strPrice);
    }

    public void close() {
        driver.quit();
    }

    private void sleep(int time) {
        try{
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
