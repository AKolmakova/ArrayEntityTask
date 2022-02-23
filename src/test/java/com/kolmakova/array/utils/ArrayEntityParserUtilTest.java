package com.kolmakova.array.utils;

import com.kolmakova.array.entity.ArrayEntity;
import com.kolmakova.array.exeption.ArrayNotExistsException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayEntityParserUtilTest {

    private ArrayEntityParserUtil arrayEntityParserUtil;

    @BeforeMethod
    public void setUp() {
        arrayEntityParserUtil = new ArrayEntityParserUtil();
    }

    @Test
    public void testParseToArrayEntityValidList() throws ArrayNotExistsException {
        //GIVEN
        List<String> list = new ArrayList<>();

        list.add("1 2 3 6 7k");
        list.add("67 5 6 102");
        list.add("6 7 8 9 10f");
        list.add("0 8 5 6 10");

        int[] expected = {67, 5, 6, 102};

        //WHEN
        ArrayEntity arrayEntity = arrayEntityParserUtil.parseToArrayEntity(list);
        int[] actual = arrayEntity.getArray();

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test ( expectedExceptions = { ArrayNotExistsException.class } )
    public void testParseToArrayEntityInvalidList() throws ArrayNotExistsException {
        //GIVEN
        List<String> list = new ArrayList<>();

        list.add("1 2 3 6 7k");
        list.add("67 5 6 102k");
        list.add("6 7 8 9 10f");
        list.add("0 8 5 6 10k");

        int[] expected = new int[0];

        //WHEN
        ArrayEntity arrayEntity = arrayEntityParserUtil.parseToArrayEntity(list);
    }
}