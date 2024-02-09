package first_test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WebDriverSeleniumDevTest {

    public static void main(String[] args) throws InterruptedException {

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

        List <WebElement> cheapGoods = waitForElementsLocatedBy(driver, By.xpath("//span[contains(text(), '₽')]/.."));
        List <String> textFromCheapGoods = cheapGoods.stream().map(WebElement::getText).collect(Collectors.toList());

        System.out.println(textFromCheapGoods);

        Thread.sleep(10000);
        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private static List <WebElement> waitForElementsLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }


}


