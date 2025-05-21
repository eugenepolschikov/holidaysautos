package com.holidaysautos.webdriver;


import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class ElementsUtil {

    private static final Logger log = LoggerFactory.getLogger(ElementsUtil.class);

    // global wait intervals across the framework
    private final static int SHORTEST_TIME_INTERVAL_SEC = 1;
    private final static int SHORT_TIME_INTERVAL_SEC = 3;
    private final static int MEDIUM_TIME_INTERVAL_SEC = 5;
    private final static int STANDARD_TIME_INTERVAL_SEC = 10;
    private final static int LONG_TIME_INTERVAL_SEC = 35;


    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        Wait<WebDriver> wait = new WebDriverWait(driver, MEDIUM_TIME_INTERVAL_SEC);
        wait.until(expectation);
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, MEDIUM_TIME_INTERVAL_SEC);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementNotPresent(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, SHORT_TIME_INTERVAL_SEC);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    public static void waitForElementNotVisible(WebDriver driver, By locator) {
        log.info("waiting until element with locator '{}' becomes invisible ", locator.toString());
        Wait<WebDriver> wait = new WebDriverWait(driver, SHORTEST_TIME_INTERVAL_SEC * 2);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForElementNotVisible(WebDriver driver, WebElement webElement) {
        Wait<WebDriver> wait = new WebDriverWait(driver, SHORTEST_TIME_INTERVAL_SEC * 2);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static WebElement fluentWait(WebDriver driver, final By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(ofSeconds(LONG_TIME_INTERVAL_SEC))
            .pollingEvery(ofSeconds(SHORTEST_TIME_INTERVAL_SEC))
            .ignoring(Exception.class);
        return wait.until(
            (Function<WebDriver, WebElement>) driver1 -> driver1.findElement(locator)
        );
    }

    public static void waitForElementGetsVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, MEDIUM_TIME_INTERVAL_SEC).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementGetsVisible(WebDriver driver, By locator) {
        new WebDriverWait(driver, MEDIUM_TIME_INTERVAL_SEC * 2).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isElementPresent(WebDriver driver, By locatorKey) {
        WebDriver.Timeouts timeouts = driver.manage().timeouts();
        timeouts.implicitlyWait(MEDIUM_TIME_INTERVAL_SEC, TimeUnit.MILLISECONDS);
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public static boolean isElementDisplayedAndEnabled(WebElement element) {
        return (element.isDisplayed() && element.isEnabled());
    }

    public static boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static void waitTillPageUrlChanges(WebDriver driver, String titlePattern) {
        log.info("waiting {} seconds until page URL contains {}", LONG_TIME_INTERVAL_SEC, titlePattern);
        WebDriverWait wait = new WebDriverWait(driver, LONG_TIME_INTERVAL_SEC);
        wait.until(ExpectedConditions.urlContains(titlePattern));
    }


    public static void waitUntilRedirectHappensFromParticularPage(WebDriver driver, String pageSubTitle) {

        WebDriverWait wait = new WebDriverWait(driver, LONG_TIME_INTERVAL_SEC);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains(pageSubTitle)));
    }

    public static void jsScrollIntoView(WebDriver driver, By locator) {
        WebElement webElement = fluentWait(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void jsScrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void jsClick(WebDriver driver, String cssSel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String javascriptCommand = String.format("var x = $('%s');x.click();", cssSel);
        js.executeScript(javascriptCommand);
    }

    public static int getShortestTimeIntervalSec() {
        return SHORTEST_TIME_INTERVAL_SEC;
    }

    public static int getShortTimeIntervalSec() {
        return SHORT_TIME_INTERVAL_SEC;
    }

    public static int getMediumTimeIntervalSec() {
        return MEDIUM_TIME_INTERVAL_SEC;
    }

    public static int getStandardTimeIntervalSec() {
        return STANDARD_TIME_INTERVAL_SEC;
    }

    public static int getLongTimeIntervalSec() {
        return LONG_TIME_INTERVAL_SEC;
    }
}