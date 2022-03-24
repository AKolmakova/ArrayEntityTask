package com.kolmakova.task2.parser;

import com.kolmakova.task2.exception.ParseXmlException;

import java.util.Arrays;

public enum FlowerXmlTag {
    FLOWERS("flowers"),
    FLOWER_PROPAGATING_BY_CUTTINGS("flower_propagating_by_cuttings"),
    FLOWER_PROPAGATING_BY_LEAVES("flower_propagating_by_leaves"),
    FLOWER_PROPAGATING_BY_SEEDS("flower_propagating_by_seeds"),
    FLOWER_GROWING_TIPS("flower_growing_tips"),
    FLOWER_NAME("name"),
    PLANT_FLOWER_COLOR("plant_flower_color"),
    STEM_COLOR("stem_color"),
    SOIL_TYPE("soil_type"),
    ORIGIN("origin"),
    FLOWER_CODE("flower_code"),
    DATE_OF_PLANTING("date_of_planting"),
    AVERAGE_FLOWER_SIZE("average_flower_size"),
    TEMPERATURE("temperature"),
    LIGHT_LOVING("light_loving"),
    WATERING("watering"),
    TYPE_OF_CUTTING("type_of_cutting"),
    LEAF_PROPAGATION_TYPE("leaf_propagation_type"),
    TYPE_OF_SEEDS("type_of_seeds");

    private static final String UNKNOWN_TAG_TEMPLATE = "Unknown tag < '%s' >";

    private final String name;

    FlowerXmlTag(String name) {
        this.name = name;
    }

    public static FlowerXmlTag getFlowerXmlTag(String name) throws ParseXmlException {
        return Arrays.stream(FlowerXmlTag.values())
                .filter(tag -> tag.name.equals(name))
                .findAny()
                .orElseThrow(() -> new ParseXmlException(String.format(UNKNOWN_TAG_TEMPLATE, name)));
    }

    public String getName() {
        return name;
    }
}
