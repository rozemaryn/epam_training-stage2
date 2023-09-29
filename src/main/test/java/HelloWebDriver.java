import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        WebElement searchInput = driver.findElement(By.xpath(
                "//span[@class='DocSearch-Button-Placeholder']"));
        searchInput.click();

        WebElement searchPanel = driver.findElement(By.className("DocSearch-Input"));
        searchPanel.sendKeys("java selenium");
        searchPanel.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        driver.quit();
    }
}
