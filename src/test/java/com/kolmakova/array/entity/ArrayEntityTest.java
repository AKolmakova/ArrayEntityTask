package com.kolmakova.array.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayEntityTest {

    private ArrayEntity arrayEntity;

    @BeforeMethod
    public void setUp() {
        arrayEntity = new ArrayEntity();
    }

    @Test
    public void testTestEqualsReflexivity() {
        //GIVEN
        int[] array = new int[]{1, 2, 3};
        ArrayEntity arrayEntity1 = new ArrayEntity(array);

        boolean expected = true;

        //WHEN
        boolean actual = arrayEntity1.equals(arrayEntity1);

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTestEqualsSymmetry() {
        //GIVEN
        int[] array = new int[]{1, 2, 3};
        ArrayEntity arrayEntity1 = new ArrayEntity(array);
        ArrayEntity arrayEntity2 = new ArrayEntity(array);

        boolean expected = arrayEntity2.equals(arrayEntity1);

        //WHEN
        boolean actual = arrayEntity1.equals(arrayEntity2);

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTestEqualsPortability() {
        //GIVEN
        int[] array = new int[]{1, 2, 3};
        ArrayEntity arrayEntity1 = new ArrayEntity(array);
        ArrayEntity arrayEntity2 = new ArrayEntity(array);
        ArrayEntity arrayEntity3 = new ArrayEntity(array);

        boolean expected = arrayEntity1.equals(arrayEntity3);

        //WHEN
        boolean actual = arrayEntity1.equals(arrayEntity2) && arrayEntity2.equals(arrayEntity3);

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTestEqualsPerConsistency() {
        //GIVEN
        int[] array = new int[]{1, 2, 3};
        ArrayEntity arrayEntity1 = new ArrayEntity(array);
        ArrayEntity arrayEntity2 = new ArrayEntity(array);

        boolean expected1 = arrayEntity1.equals(arrayEntity2);
        boolean expected2 = arrayEntity1.equals(arrayEntity2);

        //WHEN
        boolean actual1 = arrayEntity1.equals(arrayEntity2);
        boolean actual2 = arrayEntity1.equals(arrayEntity2);

        //THEN
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void testTestEqualsCompareWithNull() {
        //GIVEN
        int[] array = new int[]{1, 2, 3};
        ArrayEntity arrayEntity1 = new ArrayEntity(array);
        boolean expected = false;

        //WHEN
        boolean actual = arrayEntity1.equals(null);

        //THEN
        Assert.assertEquals(expected, actual);
    }
}