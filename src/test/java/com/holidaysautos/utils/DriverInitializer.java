package com.holidaysautos.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverInitializer {
    private final static Logger log = LoggerFactory.getLogger(DriverInitializer.class);

    public static WebDriver driverInit(String baseUrl) {
        String chromedriverSystemPropertyPath = "D:\\_selenium_stuff\\multithreaded_newer_20231019\\chromedriver.exe";
        log.info("setting chromedriver.exe property to be '{}'", chromedriverSystemPropertyPath);
        System.setProperty("webdriver.chrome.driver", chromedriverSystemPropertyPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        log.info("initializing chromedriver instance with the options");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        return driver;
    }


}
