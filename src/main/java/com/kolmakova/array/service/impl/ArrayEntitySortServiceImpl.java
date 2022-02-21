package com.kolmakova.array.service.impl;

import com.kolmakova.array.entity.ArrayEntity;
import com.kolmakova.array.service.ArrayEntitySortService;


public class ArrayEntitySortServiceImpl implements ArrayEntitySortService {

    @Override
    public ArrayEntity bubbleSort(ArrayEntity array) {
        int[] arr = array.getArray();
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        array.setArray(arr);
        return array;
    }

    @Override
    public ArrayEntity insertionSort(ArrayEntity array) {
        int[] arr = array.getArray();
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        array.setArray(arr);
        return array;
    }

    @Override
    public ArrayEntity selectionSort(ArrayEntity array) {
        int[] arr = array.getArray();
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minId = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minId = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minId] = temp;

        }
        array.setArray(arr);
        return array;
    }
}
