package com.kolmakova.array.service;

import com.kolmakova.array.entity.ArrayEntity;

public interface ArrayEntitySortService {

    ArrayEntity bubbleSort(ArrayEntity array);

    ArrayEntity insertionSort(ArrayEntity array);

    ArrayEntity selectionSort(ArrayEntity array);

}
