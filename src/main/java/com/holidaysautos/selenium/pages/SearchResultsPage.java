package com.holidaysautos.selenium.pages;

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

    public SearchResultsPage(WebDriver driver) {
        super(driver);
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
    public void extractCheapestCarByPrice() {
        // ensuring that price for filtered cars sorted ASC
        log.info("picking up the cheapest car");
        waitForPageLoaded(driver);
        // ensuring that prices are sorted in ASC
        List<WebElement> priceList = driver.findElements(By.cssSelector(PRICE_LIST_ASC_CSS));

        List<String> parsedPrices = priceList.stream().map(ele -> extractPricePerRegexp(ele.getText())).collect(Collectors.toList());

        for (String iter : parsedPrices) {
            log.info("####");
            log.info(iter);
        }
        //System.out.println("After Applying F


/*        String pattern = "\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
        String example = "€ 112";
        String updated = example.replaceAll(pattern, "$2");
        log.info("updated: {}",updated);*/
    }
}
