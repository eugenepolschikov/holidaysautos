package com.holidaysautos.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.holidaysautos.webdriver.ElementsUtil.isElementPresent;
import static com.holidaysautos.webdriver.ElementsUtil.waitForPageLoaded;

public class LandingPage extends Page {

    public static final String HOMEPATH_URL_PATH = "/home#/searchcars";

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    private final static String COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS = "#onetrust-accept-btn-handler";


    @FindBy(css = COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS)
    private WebElement cookiesDismissButton;


    @Step("dismiss cookies if present")
    public LandingPage acceptCookiesIfPresent() {
        waitForPageLoaded(driver);
        doScreenshotOfPage();
        if (isElementPresent(driver, By.cssSelector(COOKIE_ACCEPT_ALL_COOKIES_BUTTON_CSS))) {
            cookiesDismissButton.click();
        }
        doScreenshotOfPage();
        return new LandingPage(driver);
    }
}