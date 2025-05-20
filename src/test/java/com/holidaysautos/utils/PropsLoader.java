package com.holidaysautos.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsLoader {
    private final static Logger log = LoggerFactory.getLogger(PropsLoader.class);
    protected String baseUrl;

    public PropsLoader() {
        try {
            environmentPropertiesLoader();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void environmentPropertiesLoader() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream == null) {
            throw new FileNotFoundException(String.format("Property File '%s' not found in the classpath", propFileName));
        }
        prop.load(inputStream);

        baseUrl = prop.getProperty("baseUrl");
        inputStream.close();
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
