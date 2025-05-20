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


    @Step("wait for Sarch car page loaded")
    public SearchCarsPage acceptCookiesIfPresent() {
        waitForPageLoaded(driver);

        doScreenshotOfPage();

        return new SearchCarsPage(driver);
    }

    @Step("dismiss cookies if present")
    public SearchCarsPage acceptCookies() {

        waitForElementGetsVisible(driver, By.cssSelector( COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS ));
        waitForElementToBeClickable(driver, By.cssSelector( COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS ));
        waitForPageLoaded(driver);
        cookiesDismissButton.click();
        doScreenshotOfPage();
        return this;
    }
}
