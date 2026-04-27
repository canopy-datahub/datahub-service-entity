package org.canopyplatform.canopy.entityservice.util;

import org.canopyplatform.canopy.entityservice.exception.MalformedRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NEWS_SLUG_VALIDATOR = Pattern.compile("^([0-9a-zA-Z-]+)$");

    public static void validate(String value) {
        Matcher matcher = NEWS_SLUG_VALIDATOR.matcher(value);
        if(!matcher.find()){
            throw new MalformedRequestException("Invalid characters in request parameter");
        }
    }
}
