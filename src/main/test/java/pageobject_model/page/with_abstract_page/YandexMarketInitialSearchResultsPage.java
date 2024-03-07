package pageobject_model.page.with_abstract_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class YandexMarketInitialSearchResultsPage extends AbstractPage {
    public YandexMarketInitialSearchResultsPage(WebDriver driver, String term) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    protected static List<String> extractConsecutiveDigits(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\d{3,}\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    @FindBy(xpath = "//button[text()='по цене']")
    private WebElement sortByPrice;

    @FindBy(xpath = "//span[contains(text(), '?')]/..")
    private List <WebElement> cheapGoods;

    public YandexMarketInitialSearchResultsPage applyFilters() throws InterruptedException {
        sortByPrice.click();
        Thread.sleep(10000);
        return this;
    }

    public List <String> getPricesFromItems() {
        List<String> textFromCheapGoods = cheapGoods.stream().map(WebElement::getText).collect(Collectors.toList());
        return extractConsecutiveDigits(String.valueOf(textFromCheapGoods));
    }
}