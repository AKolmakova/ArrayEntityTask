package com.kolmakova.task2.entity;

import com.kolmakova.task2.exception.ParseXmlException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;

@XmlType(name = "plant_flower_color")
@XmlEnum
public enum PlantFlowerColor {
    @XmlEnumValue("Pink")
    PINK("Pink"),
    @XmlEnumValue("White")
    WHITE("White"),
    @XmlEnumValue("Red")
    RED("Red"),
    @XmlEnumValue("Yellow")
    YELLOW("Yellow");

    private static final String COLOR_NOT_FOUND_TEMPLATE = "PlantFlowerColor with name '%s' doesn't exist";
    private final String name;

    PlantFlowerColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PlantFlowerColor getPlantFlowerColor(String name) throws ParseXmlException {
        return Arrays.stream(PlantFlowerColor.values())
                .filter(color -> color.name.equals(name))
                .findAny()
                .orElseThrow(() -> new ParseXmlException(String.format(COLOR_NOT_FOUND_TEMPLATE, name)));
    }

}
