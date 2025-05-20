package com.holidaysautos.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.holidaysautos.webdriver.ElementsUtil.*;

public class SearchCarsPage extends Page {
    public SearchCarsPage(WebDriver driver) {
        super(driver);
    }

    public static final String SEARCH_CAR_PAGES_URL_PATH = "/home#/searchcars";

    private final static String COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS = "button#onetrust-accept-btn-handler";
    private final static String PICKUP_LOCATION_AUTOCOMPLETE_INPUT_CSS = "#pickupLocation";

    @FindBy(css = COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS)
    private WebElement cookiesDismissButton;
    @FindBy(css = PICKUP_LOCATION_AUTOCOMPLETE_INPUT_CSS)
    private WebElement pickupLocationAutocompleteInput;

    @Step("wait for cookies popup")
    public SearchCarsPage waitForCookiesPopup() {

        waitForElementGetsVisible(driver, By.cssSelector( COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS ));
        waitForElementToBeClickable(driver, By.cssSelector( COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS ));
        waitForPageLoaded(driver);
        return new SearchCarsPage(driver);
    }
    @Step("accept cookies popup")
    public SearchCarsPage acceptCookiesPopup(){
        cookiesDismissButton.click();
        waitForElementNotVisible(driver, By.cssSelector(COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS));

        return this;
    }


    @Step("enter search Criteria {0}")
    public void searchForParisDestination(String abc){

    }
}
