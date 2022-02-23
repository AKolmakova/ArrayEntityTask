package com.kolmakova.array.utils;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FileReaderUtilTest {

    private FileReaderUtil fileReaderUtil;

    @BeforeTest
    public void setUp() {
        fileReaderUtil = FileReaderUtil.getInstance();
    }

    @Test
    public void testReadDataFromExistingFile() {
        //GIVEN
        String path = "src/test/resources/data.txt";

        List<String> expected = new ArrayList<>();

        expected.add("1 2 3 6 7k");
        expected.add("6 7 8 9 10f");
        expected.add("0 8 5 6 10");

        //WHEN
        List<String> actual = fileReaderUtil.readDataFromFile(path);

        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadDataFromNotExistingFile() {
        //GIVEN
        String path = "src/test/resources/data1.txt";

        List<String> expected = new ArrayList<>();

        //WHEN
        List<String> actual = fileReaderUtil.readDataFromFile(path);

        //THEN
        Assert.assertEquals(expected, actual);
    }
}