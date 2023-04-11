package lesson3;

import java.util.Arrays;

public class Array<T> {
    private T[] array;

    public Array(T... array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "Array{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public array[] swapPlaces(int firstElement, int secondElement, array[]) {
        Object tmp = null;
        tmp = array[firstElement];
        array[firstElement] = array[secondElement];
        array[secondElement] = Object tmp;
        return array[];
    }
}
