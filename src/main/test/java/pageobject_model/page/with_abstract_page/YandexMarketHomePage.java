package pageobject_model.page.with_abstract_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;
import java.time.Duration;

public class YandexMarketHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://market.yandex.ru/";
    private final String term = "настольная лампа";

    @FindBy(id = "header-search")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
    }

    public YandexMarketHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public YandexMarketInitialSearchResultsPage makeInitialSearch() {
        searchInput.click();
        searchInput.sendKeys(term);
        searchButton.click();
        return new YandexMarketInitialSearchResultsPage(driver, term);
    }
}
