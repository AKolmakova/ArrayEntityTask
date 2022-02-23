package com.kolmakova.array.service.impl;

import com.kolmakova.array.entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayEntitySortServiceImplTest {

    private ArrayEntitySortServiceImpl arrayEntitySortService;

    @BeforeMethod
    public void setUp() {
        arrayEntitySortService = new ArrayEntitySortServiceImpl();
    }

    @Test
    public void testBubbleSort() {
        int[] array = {12, -34, 6, 23};
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int[] expected = {-34, 6, 12, 23};

        //WHEN
        ArrayEntity arrayEntity1 = arrayEntitySortService.bubbleSort(arrayEntity);
        int[] actual = arrayEntity1.getArray();

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInsertionSort() {
        int[] array = {12, -34, 6, 23};
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int[] expected = {-34, 6, 12, 23};

        //WHEN
        ArrayEntity arrayEntity1 = arrayEntitySortService.insertionSort(arrayEntity);
        int[] actual = arrayEntity1.getArray();

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectionSort() {
        int[] array = {12, -34, 6, 23};
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setArray(array);

        int[] expected = {-34, 6, 12, 23};

        //WHEN
        ArrayEntity arrayEntity1 = arrayEntitySortService.selectionSort(arrayEntity);
        int[] actual = arrayEntity1.getArray();

        //THEN
        Assert.assertEquals(expected, actual);
    }
}