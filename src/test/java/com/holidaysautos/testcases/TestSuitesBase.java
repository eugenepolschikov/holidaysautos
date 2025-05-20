package com.holidaysautos.testcases;


import com.holidaysautos.utils.PropsLoader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class TestSuitesBase {
    protected final static Logger log = LoggerFactory.getLogger(TestSuitesBase.class);

    protected PropsLoader configs = new PropsLoader();
    protected String baseUrl;
    protected WebDriver driver;


}
