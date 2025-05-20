package com.holidaysautos.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import static com.holidaysautos.webdriver.ElementsUtil.*;

public class SearchResultsPage extends Page {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public static final String SEARCH_CAR_RESULTS_URL_PATH = "/en/book";

    private final static String TOTAL_CARS_FOUND_CSS = "div.ctds_ts-body-m-normal.ctds_c-text-default>span,#ct-search-results ct-results-summary>div.ct-info-message>.availabilitySummaryTotal";

    @FindBy(css = TOTAL_CARS_FOUND_CSS)
    private WebElement totalCarsFound;

    @Step("checking that cars found")
    public void checkThatCarsFound() {
        waitForPageLoaded(driver);
        waitForElementNotVisible(driver, By.cssSelector(TOTAL_CARS_FOUND_CSS));
        WebElement totalCarsFoundUpd = fluentWait(driver, By.cssSelector(TOTAL_CARS_FOUND_CSS));
        int carsFound = Integer.parseInt(totalCarsFoundUpd.getText());
        log.info("total cars found '{}'", carsFound);
        Assert.assertTrue(carsFound > 0, "oops, no cars founds. Contact test developers for futher investigation");
    }


}
