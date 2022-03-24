package com.kolmakova.task2.builder;

import com.kolmakova.task2.entity.AbstractFlower;
import com.kolmakova.task2.exception.ParseXmlException;

import java.util.Set;

public interface FlowerParser {
    Set<AbstractFlower> buildFlowers() throws ParseXmlException;

    Set<AbstractFlower> getFlowers();
}
