package com.kolmakova.task2.parser;

public enum FlowerXmlAttribute {
    FLOWER_CODE("flower_code"),
    SEEDS_NAME("seeds_name");

    private final String name;

    FlowerXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
