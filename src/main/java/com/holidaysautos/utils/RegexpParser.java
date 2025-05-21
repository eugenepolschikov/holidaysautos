package com.holidaysautos.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpParser {
    private final static Logger log = LoggerFactory.getLogger(RegexpParser.class);
    public final static String PRICE_REGEXP = "\\d+(?:[.,]*\\d*)";


    public static String extractPricePerRegexp(String stringToParse) {
        log.info("parsing text by regexp '{}'", PRICE_REGEXP);
        String subStringExtracted = "";
        Pattern pattern = Pattern.compile(PRICE_REGEXP);
        Matcher m = pattern.matcher(stringToParse);
        while (m.find()) {
            subStringExtracted += m.group();
        }

        if (subStringExtracted.length() == 0) {
            log.error("ERROR: NO MATCH FOUND in '" + stringToParse + "' by regEx '" + PRICE_REGEXP + "'. " +
                "Please, contact test developers");
        }
        return subStringExtracted;
    }
}
