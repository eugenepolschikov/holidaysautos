package com.holidaysautos.testcases.functionalui;

import com.holidaysautos.core.Screen;
import com.holidaysautos.models.CarDto;
import com.holidaysautos.models.CarSearchCriteriaDto;
import com.holidaysautos.selenium.pages.LandingPage;
import com.holidaysautos.selenium.pages.SearchCarsPage;
import com.holidaysautos.selenium.pages.SearchResultsPage;
import com.holidaysautos.testcases.TestSuitesBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;


public class LandingPageTests extends TestSuitesBase implements Screen {

    CarSearchCriteriaDto parisCity = new CarSearchCriteriaDto("Paris");
    CarSearchCriteriaDto tripSummarySetStepOne;
    CarDto cheapestCar;

    @Title("search cars generic tests")
    @Features("landing and search cars tests automation")
    @Stories("User is able to search cars")
    @Test(groups = {"E2E"})
    @Parameters({"browser"})
    public void carsSearchTest() {

        tripSummarySetStepOne = new LandingPage(driver)
            .searchCarPageRedirect()
            .waitForCookiesPopup()
            .acceptCookiesPopup()
            .searchForParisDestination(parisCity)
            .pickupFirstLocationOptionHappyPath(parisCity)
            .extractTheDataSetForStepOne();

        new SearchCarsPage(driver)
            .searchCarsClick();

    }


    @Title("pick up the car from search")
    @Features("car list retrieves after the search")
    @Stories("User is able to pick up cheapest car")
    @Test(groups = {"E2E"})
    @Parameters({"browser"})
    public void findCheapestCarTest() {
        cheapestCar = new SearchResultsPage(driver)
            .checkThatCarsFound()
            .sortByPrice()
            .extractCheapestCarByPrice();
    }

    @Title("validate selected car price and trip summary")
    @Features("car options opens after car selection")
    @Stories("User is able to select car option during the order")
    @Test(groups = {"E2E"})
    @Parameters({"browser"})
    public void findCheapestCarValidationTest() {
        SoftAssert softAssertion = new SoftAssert();
        Assert.assertNotNull(cheapestCar);
        Assert.assertNotNull(tripSummarySetStepOne);

        new SearchResultsPage(driver).firstCarInTheListClick()
            .validateCarSelected(cheapestCar, softAssertion)
        .validateDateRanges(tripSummarySetStepOne, softAssertion );

        softAssertion.assertAll();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}