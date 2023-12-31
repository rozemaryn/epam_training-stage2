import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        WebElement searchInput = driver.findElement(By.xpath(
                "//span[@class='DocSearch-Button-Placeholder']"));
        searchInput.click();

        List<WebElement> searchPanel = driver.findElements(By.className("DocSearch-Input"));
        searchPanel.get(0).sendKeys("java selenium");
        searchPanel.get(0).sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        driver.quit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
}
