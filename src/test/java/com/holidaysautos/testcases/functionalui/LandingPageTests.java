package com.holidaysautos.testcases.functionalui;

import com.holidaysautos.core.Screen;
import com.holidaysautos.selenium.pages.LandingPage;
import com.holidaysautos.testcases.TestSuitesBase;
import com.holidaysautos.utils.DriverInitializer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.holidaysautos.webdriver.ElementsUtil.waitForPageLoaded;


public class LandingPageTests extends TestSuitesBase implements Screen {

    @BeforeTest(groups = {"END.TO.END"})
    public void chromeDriverInit() {

        baseUrl = configs.getBaseUrl();
        log.info("driver init and opening baseUrl: '{}'", baseUrl);
        driver = DriverInitializer.driverInit(baseUrl);
        log.info("test started. Current thread: {}", Thread.currentThread().getId());
        driver.get(baseUrl);
        waitForPageLoaded(driver);

    }

    @AfterTest(groups = {"END.TO.END"})
    public void closeDriverInstance() {

        driver.quit();
    }

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
            .acceptCookies();


        softAssertion.assertAll();

    }


    @Override
    public WebDriver getDriver() {
        return driver;
    }
}