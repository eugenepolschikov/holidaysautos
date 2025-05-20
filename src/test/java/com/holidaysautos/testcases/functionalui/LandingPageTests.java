package com.holidaysautos.testcases.functionalui;

import com.holidaysautos.core.Screen;
import com.holidaysautos.selenium.LandingPage;
import com.holidaysautos.testcases.TestSuitesBase;
import com.holidaysautos.utils.DriverInitializer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.holidaysautos.webdriver.ElementsUtil.waitForPageLoaded;


public class LandingPageTests extends TestSuitesBase implements Screen {


    @Title("landing page generic tests flow ")
    @Features("landing automation")
    @Stories("landing page verification")
    @Test(groups = {"END.TO.END"})
    @Parameters({"browser"})
    public void landingPageTest() {

        baseUrl = configs.getBaseUrl();
        log.info("driver init and opening baseUrl: '{}'", baseUrl);
        driver = DriverInitializer.driverInit(baseUrl);
        log.info("test started. Current thread: {}", Thread.currentThread().getId());
        driver.get(baseUrl);
        waitForPageLoaded(driver);


        SoftAssert softAssertion = new SoftAssert();
        log.info("test is starting {}", "now");


        new LandingPage(driver).acceptCookiesIfPresent();

        softAssertion.assertAll();

        driver.quit();
    }


    @Override
    public WebDriver getDriver() {
        return driver;
    }
}