package com.kolmakova.array.service.impl;

import com.kolmakova.array.entity.ArrayEntity;
import com.kolmakova.array.utils.ArrayEntityParserUtil;
import com.kolmakova.array.utils.FileReaderUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class ArrayEntityServiceImplTest {

    private FileReaderUtil fileReaderUtil;
    private ArrayEntityParserUtil parserUtil;
    private ArrayEntityServiceImpl entityService;

    @BeforeTest
    public void setUp() {
        fileReaderUtil = mock(FileReaderUtil.class);
        parserUtil = mock(ArrayEntityParserUtil.class);

        entityService = new ArrayEntityServiceImpl(fileReaderUtil, parserUtil);
    }

    @Test
    public void testGetMinValue() {
        //GIVEN
        int[] array = {12, 34, 6, 23};

        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int expected = 6;

        //WHEN
        int actual = entityService.getMinValue(arrayEntity);

        //THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetMaxValue() {
        //GIVEN
        int[] array = {12, 34, 6, 23};

        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int expected = 34;

        //WHEN
        int actual = entityService.getMaxValue(arrayEntity);

        //THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetAverageValue() {
        //GIVEN
        int[] array = {12, 34, 6, 23};

        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);
        arrayEntity.setLength(array.length);

        int expected = 18;

        //WHEN
        int actual = entityService.getAverageValue(arrayEntity);

        //THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetSumOfElements() {
        //GIVEN
        int[] array = {12, 34, 6, 23};

        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int expected = 75;

        //WHEN
        int actual = entityService.getSumOfElements(arrayEntity);

        //THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetNumberOfPositiveElements() {
        //GIVEN
        int[] array = {12, 34, 6, 23};

        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int expected = 4;

        //WHEN
        int actual = entityService.getNumberOfPositiveElements(arrayEntity);

        //THEN
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void exchangePositiveValuesToOne() {
        //GIVEN
        int[] array = {12, -34, 6, 23};
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int[] expected = {1, 1, 1};

        //WHEN
        ArrayEntity arrayEntity1 = entityService.exchangePositiveValuesToOne(arrayEntity);
        int[] actual = arrayEntity1.getArray();

        //THEN
        Assert.assertEquals(expected, actual);
    }
}