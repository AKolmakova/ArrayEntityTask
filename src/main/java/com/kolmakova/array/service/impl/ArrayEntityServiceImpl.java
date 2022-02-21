package com.kolmakova.array.service.impl;

import com.kolmakova.array.config.ApplicationConfig;
import com.kolmakova.array.entity.ArrayEntity;
import com.kolmakova.array.exeption.ArrayNotExistsException;
import com.kolmakova.array.exeption.IllegalEntityStateException;
import com.kolmakova.array.service.ArrayEntityService;
import com.kolmakova.array.utils.ArrayEntityParserUtil;
import com.kolmakova.array.utils.FileReaderUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayEntityServiceImpl implements ArrayEntityService {

    static {
        ApplicationConfig.initLogger();
    }

    private FileReaderUtil fileReaderUtil;
    private ArrayEntityParserUtil parserUtil;

    public ArrayEntityServiceImpl(FileReaderUtil fileReaderUtil, ArrayEntityParserUtil parserUtil) {
        this.fileReaderUtil = fileReaderUtil;
        this.parserUtil = parserUtil;
    }

    @Override
    public ArrayEntity readFromFile(String filePath) throws ArrayNotExistsException {
        List<String> strings = fileReaderUtil.readDataFromFile(filePath);

        return parserUtil.parseToArrayEntity(strings);
    }

    @Override
    public int getMinValue(ArrayEntity array) {
        int[] arr = array.getArray();
        return Arrays
                .stream(arr)
                .min()
                .orElseThrow(this::throwException);
    }

    @Override
    public int getMaxValue(ArrayEntity array) {
        int[] arr = array.getArray();
        return IntStream.of(arr)
                .max()
                .orElseThrow(this::throwException);
    }

    @Override
    public int getAverageValue(ArrayEntity array) {
        int sum = getSumOfElements(array);
        int numberOfElements = array.getLength();

        return sum / numberOfElements;
    }

    @Override
    public int getSumOfElements(ArrayEntity array) {
        int[] arr = array.getArray();

        return IntStream.of(arr)
                .sum();
    }

    @Override
    public int getNumberOfPositiveElements(ArrayEntity array) {
        int[] arr = array.getArray();
        return (int) IntStream.of(arr)
                .filter(this::isPositive)
                .count();
    }

    @Override
    public ArrayEntity exchangePositiveValuesToOne(ArrayEntity array) {
        int[] arr = array.getArray();

        int[] updatedArray = IntStream.of(arr)
                .filter(this::isPositive)
                .map(this::replaceToOne)
                .toArray();

        array.setArray(updatedArray);
        return array;
    }

    private IllegalEntityStateException throwException() {
        throw new IllegalEntityStateException("Array can not be empty or null!");
    }

    private boolean isPositive(int element) {
        return element >= 0;
    }

    private int replaceToOne(int element) {
        return 1;
    }

}
