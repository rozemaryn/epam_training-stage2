package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSyncDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://selenium.dev");
        WebElement searchInput = driver.findElement(By.xpath(
                "//span[@class='DocSearch-Button-Placeholder']"));
        searchInput.click();

        List<WebElement> searchPanel = driver.findElements(By.className("DocSearch-Input"));
        searchPanel.get(0).sendKeys("java selenium");
        searchPanel.get(0).sendKeys(Keys.ENTER);

        driver.quit();
    }
}
