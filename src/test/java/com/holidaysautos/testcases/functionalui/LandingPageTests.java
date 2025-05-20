package com.holidaysautos.testcases.functionalui;

import com.holidaysautos.core.Screen;
import com.holidaysautos.selenium.pages.LandingPage;
import com.holidaysautos.testcases.TestSuitesBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;


public class LandingPageTests extends TestSuitesBase implements Screen {


    @Title("landing page generic tests flow ")
    @Features("landing automation")
    @Stories("landing page verification")
    @Test(groups = {"END.TO.END"})
    @Parameters({"browser"})
    public void searchCarsByLocationAndDateRangesTests() {

        //@TODO add validation within the test
        SoftAssert softAssertion = new SoftAssert();
        log.info("test is starting {}", "now");


        new LandingPage(driver)
            .searchCarPageRedirect()
            .waitForCookiesPopup()   //checkk for visibility of cookies popup
            .acceptCookiesPopup()    // check that cookies popup disappeared
            .searchForParisDestination("Paris");

        softAssertion.assertAll();

    }


    @Override
    public WebDriver getDriver() {
        return driver;
    }
}