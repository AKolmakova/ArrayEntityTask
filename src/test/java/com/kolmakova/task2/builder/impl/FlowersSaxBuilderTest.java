package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.AbstractFlowersTestConfig;
import com.kolmakova.task2.builder.FlowersDataProvider;
import com.kolmakova.task2.entity.AbstractFlower;
import com.kolmakova.task2.exception.ParseXmlException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class FlowersSaxBuilderTest extends AbstractFlowersTestConfig {

    private FlowersSaxBuilder flowersSaxBuilder;

    @BeforeMethod
    public void setUp() throws ParseXmlException {
        flowersSaxBuilder = new FlowersSaxBuilder(CORRECT_RESOURCE_NAME, SCHEMA_NAME);
    }

    @Test(dataProvider = "flowers_data", dataProviderClass = FlowersDataProvider.class)
    public void testGetFlowers(AbstractFlower expectedFlower) throws ParseXmlException {
        Set<AbstractFlower> flowers = flowersSaxBuilder.buildFlowers();
        assertNotNull(flowers);
        assertEquals(flowers.size(), 3);
        assertHasFlower(flowers, expectedFlower);
    }
}