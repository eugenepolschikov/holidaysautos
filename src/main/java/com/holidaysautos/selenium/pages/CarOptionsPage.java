package com.holidaysautos.selenium.pages;

import com.holidaysautos.models.CarDto;
import com.holidaysautos.models.CarSearchCriteriaDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import static com.holidaysautos.utils.RegexpParser.extractPricePerRegexp;
import static com.holidaysautos.webdriver.ElementsUtil.fluentWait;
import static com.holidaysautos.webdriver.ElementsUtil.waitForElementGetsVisible;

public class CarOptionsPage extends Page {
    public CarOptionsPage(WebDriver driver) {
        super(driver);
    }

    public static final String CARNAME_OR_MODE_CSS = "div[data-auto-id=\"ct-vehicle-block-title\"]";
    public static final String CAR_TOTAL_PRICE_CSS = "div.ct-total-price";
    public static final String TRIP_LOCATION_ACTUAL_CSS = "div.ct-panel-trip-summary--item[data-auto-id=\"pickUpLocationReadOnly\"] div.ct-panel-trip-summary--location-name";
    public static final String PICKUP_DATE_CSS = "div.ct-panel-trip-summary--item[data-auto-id=\"pickUpLocationReadOnly\"] [data-auto-id=\"searchFormPickupDateReadOnly\"]";
    public static final String PICKUP_TIME_CSS = "[data-auto-id=pickUpLocationReadOnly] [data-auto-id=\"searchFormPickupTimeReadOnly\"]";
    public static final String RETURN_DATE_CSS = "div.ct-panel-trip-summary--item[data-auto-id=\"pickUpLocationReadOnly\"] [data-auto-id=\"searchFormPickupDateReadOnly\"]";
    public static final String RETURN_TIME_CSS = "[data-auto-id=dropOffUpLocationReadOnly] [data-auto-id=\"searchFormReturnTimeReadOnly\"]";
    @FindBy(css = CARNAME_OR_MODE_CSS)
    private WebElement carModel;
    ;

    public CarOptionsPage validateCarSelected(CarDto expected, SoftAssert softAssert) {
        waitForElementGetsVisible(driver, By.cssSelector(CARNAME_OR_MODE_CSS));
        CarDto carActual = new CarDto();
        carActual.setCarModelDescription(fluentWait(driver, By.cssSelector(CARNAME_OR_MODE_CSS)).getText());
        carActual.setCarPrice(extractPricePerRegexp(fluentWait(driver, By.cssSelector(CAR_TOTAL_PRICE_CSS)).getText()));
        log.info("comparing INITIAL car selection '{}' with current car '{}'", expected, carActual);
        softAssert.assertEquals(expected, carActual);
        return this;
    }

    public void validateDateRanges(CarSearchCriteriaDto expectedTripSummary, SoftAssert softAssert) {
        CarSearchCriteriaDto actualTripSummary = new CarSearchCriteriaDto();
        actualTripSummary.setLocation(fluentWait(driver, By.cssSelector(TRIP_LOCATION_ACTUAL_CSS)).getText());

        WebElement pickupDate = fluentWait(driver, By.cssSelector(PICKUP_DATE_CSS));
        actualTripSummary.setPickupDate((pickupDate.getText().split(","))[1]);

        WebElement pickupTime = fluentWait(driver, By.cssSelector(PICKUP_TIME_CSS));
        actualTripSummary.setPickupTime(pickupTime.getText());

        WebElement returnDate = fluentWait(driver, By.cssSelector(RETURN_DATE_CSS));
        actualTripSummary.setReturnDate((returnDate.getText().split(","))[1]);

        WebElement returnTime = fluentWait(driver, By.cssSelector(RETURN_TIME_CSS));
        actualTripSummary.setReturnTime(returnTime.getText());

        log.info("comparing INITIAL trip summary '{}' with current trip summary '{}'",
            expectedTripSummary.toStringAllFields(), actualTripSummary.toStringAllFields());
        softAssert.assertEquals(expectedTripSummary, actualTripSummary);

    }
}
