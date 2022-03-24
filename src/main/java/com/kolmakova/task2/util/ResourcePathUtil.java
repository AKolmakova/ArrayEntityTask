package com.kolmakova.task2.util;

import com.kolmakova.task2.exception.ParseXmlException;

import java.io.File;
import java.net.URL;

public class ResourcePathUtil {

    public static String getResourcePath(String resourceName) throws ParseXmlException {
        URL url = ResourcePathUtil.class
                .getClassLoader()
                .getResource(resourceName);
        if (url == null) {
            throw new ParseXmlException(String.format("Resource '%s' is not found.",resourceName));
        }

        return new File(url.getFile()).getAbsolutePath();
    }
}
