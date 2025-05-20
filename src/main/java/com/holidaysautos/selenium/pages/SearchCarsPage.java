package com.holidaysautos.selenium.pages;

import com.holidaysautos.models.CarSearchCriteriaDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;

import static com.holidaysautos.selenium.pages.SearchResultsPage.SEARCH_CAR_RESULTS_URL_PATH;
import static com.holidaysautos.webdriver.ElementsUtil.*;

public class SearchCarsPage extends Page {
    CarSearchCriteriaDto searchCriteriaSetStepOne;

    public SearchCarsPage(WebDriver driver) {
        super(driver);
        searchCriteriaSetStepOne = new CarSearchCriteriaDto();
    }

    public static final String SEARCH_CAR_PAGES_URL_PATH = "/home#/searchcars";

    private final static String COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS = "button#onetrust-accept-btn-handler";
    private final static String PICKUP_LOCATION_AUTOCOMPLETE_INPUT_CSS = "#pickupLocation";
    private final static String LOCATION_OPTIONS_CSS = "ul>li[id*=item-]";
    private final static String PICKUP_DATE_INPUT_CSS = "#pickupDate";
    private final static String PICKUP_TIME_INPUT_CSS = "#pickupTime";
    private final static String RETURN_DATE_INPUT_CSS = "#returnDate";
    private final static String RETURN_TIME_INPUT_CSS = "#returnTime";
    private final static String SEARCH_BUTTON_CSS = "button#searchCarsFormBtn-searchcars";

    @FindBy(css = COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS)
    private WebElement cookiesDismissButton;
    @FindBy(css = PICKUP_LOCATION_AUTOCOMPLETE_INPUT_CSS)
    private WebElement pickupLocationAutocompleteInput;
    @FindBy(css = SEARCH_BUTTON_CSS)
    private WebElement searchButton;


    @Step("searchcars: wait for cookies popup")
    public SearchCarsPage waitForCookiesPopup() {

        waitForElementGetsVisible(driver, By.cssSelector(COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS));
        waitForElementToBeClickable(driver, By.cssSelector(COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS));
        waitForPageLoaded(driver);
        return new SearchCarsPage(driver);
    }

    @Step("searchcars: accept cookies popup")
    public SearchCarsPage acceptCookiesPopup() {
        cookiesDismissButton.click();
        waitForElementNotVisible(driver, By.cssSelector(COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS));

        return this;
    }


    @Step("searchcars: enter search Criteria {0}")
    public SearchCarsPage searchForParisDestination(CarSearchCriteriaDto searchCriteria) {
        log.info("entering lookup data criteria: {}", searchCriteria.getLocation());
        pickupLocationAutocompleteInput.sendKeys(searchCriteria.getLocation());
        waitForPageLoaded(driver);

        return new SearchCarsPage(driver);
    }

    @Step("searchcars: pick up the first option by Criteria lookup {0}")
    public SearchCarsPage pickupFirstLocationOptionHappyPath(CarSearchCriteriaDto searchCriteria) {


        log.info("checking that some options popped up for the given location criteria");
        List<WebElement> locationOptionsDdl = driver.findElements(By.cssSelector(LOCATION_OPTIONS_CSS));
        log.info("checking that some options appeared per the given search criteria: '{}'", searchCriteria);
        Assert.assertTrue(locationOptionsDdl.size() > 0, String.format("oops , no options have been retrived for the location '%s'. Please contact test developers for further investigation", searchCriteria));
        WebElement firstOption = locationOptionsDdl.get(0);
        log.info("clicking on first option retrieved '{}'", firstOption.getText());
        searchCriteriaSetStepOne.setLocation(firstOption.getText());
        firstOption.click();
        waitForPageLoaded(driver);

        return this;
    }

    @Step("searchcars: extracting values set from search page")
    public CarSearchCriteriaDto extractTheDataSetForStepOne() {
        searchCriteriaSetStepOne.setPickupDate(driver.findElement(By.cssSelector(PICKUP_DATE_INPUT_CSS)).getAttribute("value"));
        searchCriteriaSetStepOne.setPickupTime(driver.findElement(By.cssSelector(PICKUP_TIME_INPUT_CSS)).getAttribute("value"));
        searchCriteriaSetStepOne.setReturnDate(driver.findElement(By.cssSelector(RETURN_DATE_INPUT_CSS)).getAttribute("value"));
        searchCriteriaSetStepOne.setReturnTime(driver.findElement(By.cssSelector(RETURN_TIME_INPUT_CSS)).getAttribute("value"));
        log.info("data set for all fields, step one '{}'", searchCriteriaSetStepOne.toStringAllFields());
        postSearchCriteriaSetStepOne(searchCriteriaSetStepOne);
        return searchCriteriaSetStepOne;
    }

    @Step("searchcars: search button click")
    public SearchResultsPage searchCarsClick(){
        log.info("clicking search button");
        searchButton.click();
        waitTillPageUrlChanges(driver, SEARCH_CAR_RESULTS_URL_PATH);
        waitForPageLoaded(driver);
        return new SearchResultsPage(driver);
    }

    @Attachment(value = "recording extracted values", type = "text/html")
    public byte[] postSearchCriteriaSetStepOne(CarSearchCriteriaDto searchCriteriaActual) {

        return (searchCriteriaActual.toStringAllFields()).getBytes(Charset.forName("UTF-8"));
    }
}
