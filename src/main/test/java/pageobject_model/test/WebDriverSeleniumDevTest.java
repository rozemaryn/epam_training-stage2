package pageobject_model.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject_model.page.with_abstract_page.YandexMarketHomePage;
import java.util.List;


public class WebDriverSeleniumDevTest {
    private WebDriver driver;

    @BeforeEach
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void isCheapestLampUnder500Rubles() throws InterruptedException {
        List<String> pricesFromCheapGoods = new YandexMarketHomePage(driver)
                .openPage()
                .makeInitialSearch()
                .applyFilters()
                .getPricesFromItems();

        Assertions.assertTrue(Integer.parseInt(pricesFromCheapGoods.get(0)) < 500);
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}


