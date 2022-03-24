package com.kolmakova.task2;

import com.kolmakova.task2.entity.AbstractFlower;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public abstract class AbstractFlowersTestConfig {

    public static final String CORRECT_RESOURCE_NAME = "task2/flowersTest.xml";
    public static final String SCHEMA_NAME = "task2/flowers.xsd";

    protected void assertHasFlower(Set<AbstractFlower> actualFlowers, AbstractFlower expected) {
        AbstractFlower actualFlower = actualFlowers.stream()
                .filter(flower -> flower.getFlowerCode().equals(expected.getFlowerCode()))
                .findAny()
                .orElse(null);

        assertEquals(expected, actualFlower);
    }
}
