package first_test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebDriverSeleniumDevTest {

    private static WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void isCheapestLampUnder500Rubles() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://market.yandex.ru/");

        WebElement searchInput = waitForElementLocatedBy(driver, By.xpath(
                "//input[@id='header-search']"));
        searchInput.click();
        searchInput.sendKeys("настольная лампа");

        WebElement searchButton = waitForElementLocatedBy(driver, By.xpath("//button[@type='submit']"));
        searchButton.click();

        WebElement sortByPrice = waitForElementLocatedBy(driver, By.xpath("//button[text()='по цене']"));
        sortByPrice.click();

        Thread.sleep(10000);
        List<WebElement> cheapGoods = waitForElementsLocatedBy(driver, By.xpath("//span[contains(text(), '₽')]/.."));
        List<String> textFromCheapGoods = cheapGoods.stream().map(WebElement::getText).collect(Collectors.toList());

        List <String> onlyDigits = extractConsecutiveDigits(String.valueOf(textFromCheapGoods));

        Assert.assertTrue(Integer.parseInt(onlyDigits.get(0)) < 500);
    }

    @AfterMethod (alwaysRun = true)
        public void closeBrowser(){
            driver.quit();
            driver = null;
        }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement findStaleElement(String locator) throws Exception {
        WebDriver driver = new ChromeDriver();

        for (int i = 1; i <= 10; i++)
        {
            try
            {
                System.out.println("Trying to find element " + i + " time");
                return waitForElementLocatedBy(driver, By.xpath(locator));
            }
            catch (StaleElementReferenceException e)
            {
                System.out.println("StaleElementReferenceException was thrown: try #{i}/{RetryNumber}.");
            }
        }
        throw new Exception("Element is stale");
    }

    public static List<String> extractConsecutiveDigits(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\d{3,}\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}


