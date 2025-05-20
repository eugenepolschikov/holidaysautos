package com.holidaysautos.testcases;


import com.holidaysautos.utils.DriverInitializer;
import com.holidaysautos.utils.PropsLoader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.holidaysautos.webdriver.ElementsUtil.waitForPageLoaded;


public abstract class TestSuitesBase {
    protected final static Logger log = LoggerFactory.getLogger(TestSuitesBase.class);

    protected PropsLoader configs = new PropsLoader();
    protected String baseUrl;
    protected WebDriver driver;

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

}
