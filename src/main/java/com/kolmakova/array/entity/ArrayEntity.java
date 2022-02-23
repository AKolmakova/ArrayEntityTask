package com.kolmakova.array.entity;

import java.util.Arrays;

public class ArrayEntity {
    private int[] array;
    private int length;

    public ArrayEntity(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    public ArrayEntity() {

    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayEntity that = (ArrayEntity) o;

        return Arrays.equals(array, that.array);
    }
}
