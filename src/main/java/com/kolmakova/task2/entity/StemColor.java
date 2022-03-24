package com.kolmakova.task2.entity;

import com.kolmakova.task2.exception.ParseXmlException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;

@XmlType(name = "stem_color")
@XmlEnum
public enum StemColor {
    @XmlEnumValue("Green")
    GREEN("Green"),
    @XmlEnumValue("Brown")
    BROWN("Brown"),
    @XmlEnumValue("LightGreen")
    LIGHT_GREEN("LightGreen");

    private static final String STEM_COLOR_NOT_FOUND_TEMPLATE = "StemColor with name '%s' doesn't exist";

    private final String name;

    StemColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static StemColor getStemColor(String name) throws ParseXmlException {
        return Arrays.stream(StemColor.values())
                .filter(color -> color.name.equals(name))
                .findAny()
                .orElseThrow(() -> new ParseXmlException(String.format(STEM_COLOR_NOT_FOUND_TEMPLATE, name)));
    }

}
