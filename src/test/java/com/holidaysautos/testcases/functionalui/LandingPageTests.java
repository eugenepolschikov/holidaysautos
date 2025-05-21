package com.holidaysautos.testcases.functionalui;

import com.holidaysautos.core.Screen;
import com.holidaysautos.models.CarSearchCriteriaDto;
import com.holidaysautos.selenium.pages.LandingPage;
import com.holidaysautos.selenium.pages.SearchCarsPage;
import com.holidaysautos.selenium.pages.SearchResultsPage;
import com.holidaysautos.testcases.TestSuitesBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;


public class LandingPageTests extends TestSuitesBase implements Screen {

    CarSearchCriteriaDto parisCity = new CarSearchCriteriaDto("Paris");
    CarSearchCriteriaDto searchCriteriaSetStepOne;

    @Title("search cars generic tests")
    @Features("landing and search cars tests automation")
    @Stories("User is able to search cars")
    @Test(groups = {"E2E"})
    @Parameters({"browser"})
    public void carsSearchTest() {


        //@TODO add validation within the test
//        SoftAssert softAssertion = new SoftAssert();

        searchCriteriaSetStepOne = new LandingPage(driver)
            .searchCarPageRedirect()
            .waitForCookiesPopup()   // @TODO check for visibility of cookies popup
            .acceptCookiesPopup()    // @TODO check that cookies popup disappeared
            .searchForParisDestination(parisCity)
            .pickupFirstLocationOptionHappyPath(parisCity)
            .extractTheDataSetForStepOne();

        new SearchCarsPage(driver)
            .searchCarsClick();
//        softAssertion.assertAll();
    }


    @Title("pick up the car from search")
    @Features("car list retrieves after the search")
    @Stories("User is able to pick up cheapest car")
    @Test(groups = {"E2E"})
    @Parameters({"browser"})
    public void findCheapestCarTest() throws InterruptedException {
        new SearchResultsPage(driver)
            .checkThatCarsFound()
            .sortByPrice()
            .extractCheapestCarByPrice();

        Thread.sleep(1500);

    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}