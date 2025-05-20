package com.holidaysautos.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.holidaysautos.selenium.pages.SearchCarsPage.SEARCH_CAR_PAGES_URL_PATH;
import static com.holidaysautos.webdriver.ElementsUtil.*;

public class LandingPage extends Page {

    public static final String HOMEPATH_URL_PATH = "/home#/searchcars";

    public LandingPage(WebDriver driver) {
        super(driver);
    }


    @Step("wait for automatic redirect to search car page module")
    public SearchCarsPage searchCarPageRedirect(){
        waitTillPageUrlChanges(driver, SEARCH_CAR_PAGES_URL_PATH);
        return new SearchCarsPage(driver);
    }

}