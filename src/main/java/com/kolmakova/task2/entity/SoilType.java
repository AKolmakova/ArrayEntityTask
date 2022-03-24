package com.kolmakova.task2.entity;

import com.kolmakova.task2.exception.ParseXmlException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;

@XmlType(name = "soil_type")
@XmlEnum
public enum SoilType {
    @XmlEnumValue("Ground")
    GROUND("Ground"),
    @XmlEnumValue("Podzol")
    PODZOL("Podzol"),
    @XmlEnumValue("SoddyPodzol")
    SODDY_PODZOL("SoddyPodzol");

    private static final String SOIL_TYPE_NOT_FOUND_TEMPLATE = "SoilType with name '%s' doesn't exist";

    private final String  name;

    SoilType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SoilType getSoilType(String name) throws ParseXmlException {
        return Arrays.stream(SoilType.values())
                .filter(type -> type.name.equals(name))
                .findAny()
                .orElseThrow(() -> new ParseXmlException(String.format(SOIL_TYPE_NOT_FOUND_TEMPLATE, name)));
    }

}
