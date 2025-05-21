package com.holidaysautos.selenium.pages;

import com.holidaysautos.models.CarDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.holidaysautos.utils.RegexpParser.extractPricePerRegexp;
import static com.holidaysautos.webdriver.ElementsUtil.fluentWait;
import static com.holidaysautos.webdriver.ElementsUtil.waitForPageLoaded;

public class SearchResultsPage extends Page {

    public static final String BTN_UNSELECTED_CSS_STYLE = "ct-sort-buttons__unselected";
    public static final String BTN_SELECTED_CSS_STYLE = "ctc-button ctc-button--primary";
    public static final String PRICE_LIST_ASC_CSS = "div[ct-include*=\"vehicleBlock\"] div.ct-total-price";
    public static final String MODEL_CAR_LIST_SHORT_DESCRT_CSS = "div[data-auto-id=\"ct-vehicle-block-title\"]";
    public static final String BOOK_BUTTON_LIST_CSS = "button[ct-tracking=\"book_btn\"]";

    private CarDto carModel;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        carModel = new CarDto();
    }

    public static final String SEARCH_CAR_RESULTS_URL_PATH = "/en/book";

    private final static String TOTAL_CARS_FOUND_CSS = "div.ctds_ts-body-m-normal.ctds_c-text-default>span,#ct-search-results ct-results-summary>div.ct-info-message>.availabilitySummaryTotal";
    private final static String PRICE_LOW_TO_HIGH_CSS = "[display-points-sort-btn=\"displayPointsSortButton\"] button[data-auto-id=\"Sort by Price\"]";

    @FindBy(css = TOTAL_CARS_FOUND_CSS)
    private WebElement totalCarsFound;
    @FindBy(css = PRICE_LOW_TO_HIGH_CSS)
    private WebElement priceSort;

    @Step("checking that cars found")
    public SearchResultsPage checkThatCarsFound() {
        waitForPageLoaded(driver);
        WebElement totalCarsFoundUpd = fluentWait(driver, By.cssSelector(TOTAL_CARS_FOUND_CSS));
        int carsFound = Integer.parseInt(totalCarsFoundUpd.getText());
        log.info("total cars found '{}'", carsFound);
        Assert.assertTrue(carsFound > 0, "oops, no cars founds. Contact test developers for futher investigation");
        return this;
    }

    @Step("sort by price")
    public SearchResultsPage sortByPrice() {
        log.info("clicking on sort by price");
        priceSort.click();
        waitForPageLoaded(driver);
        priceSort = driver.findElement(By.cssSelector(PRICE_LOW_TO_HIGH_CSS));
        log.info("checking that button DONT have CSS class '{}' and have '{}' class applied",
            BTN_UNSELECTED_CSS_STYLE, BTN_SELECTED_CSS_STYLE);
        String cssStylesAfterSort = priceSort.getAttribute("class");
        log.info("styles Price low to high btn '{}'", cssStylesAfterSort);
        Assert.assertTrue(!cssStylesAfterSort.contains(BTN_UNSELECTED_CSS_STYLE) &&
            cssStylesAfterSort.contains(BTN_SELECTED_CSS_STYLE));

        return new SearchResultsPage(driver);
    }

    @Step("Extract cheapest car by price")
    public CarDto extractCheapestCarByPrice() {

        waitForPageLoaded(driver);
        log.info("ensuring that prices are sorted in ASC");
        List<WebElement> priceList = driver.findElements(By.cssSelector(PRICE_LIST_ASC_CSS));

        List<String> parsedPrices = priceList.stream().map(ele -> extractPricePerRegexp(ele.getText())).collect(Collectors.toList());
        log.info("ensuring that prices parsed ASC");
        Assert.assertTrue(priceList.size() > 0, "oops, no prices for the cars been found. Contact test decelopers for investigation");
        if (priceList.size() > 1) {
            for (int iter = 0; iter < parsedPrices.size() - 1; iter++) {
                Assert.assertTrue(Float.parseFloat(String.valueOf(parsedPrices.get(iter))) <= Float.parseFloat(String.valueOf(parsedPrices.get(iter + 1))), "OOPS prices are not sorted ASC. Please contact test developers for investigation");
            }
        }

        List<WebElement> carModelList = driver.findElements(By.cssSelector(MODEL_CAR_LIST_SHORT_DESCRT_CSS));

        carModel.setCarPrice(parsedPrices.get(0));
        carModel.setCarModelDescription(carModelList.get(0).getText());
        log.info("picking up the cheapest car '{}'", carModel);

        return carModel;
    }

    public CarOptionsPage firstCarInTheListClick() {
        log.info("picking up the first car in the list displayed");

        List<WebElement> carList = driver.findElements(By.cssSelector(BOOK_BUTTON_LIST_CSS));
        carList.get(0).click();
        waitForPageLoaded(driver);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        return new CarOptionsPage(driver);

    }
}
