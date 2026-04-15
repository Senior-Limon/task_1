package org.example.customArray;

import org.example.customException.IntegerDataVectorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class IntegerDataVector {

    private static final Logger log = LogManager.getLogger(IntegerDataVector.class);
    private  int[] array;

    private IntegerDataVector (int size) {
        array = new int[size];
    }

    private IntegerDataVector (int... inputData){
        array = inputData.clone();
    }

    public static IntegerDataVector createBySize(int size) {
        if (size < 0) {
            throw new IntegerDataVectorException("Trying create vector with negative size");
        }
        log.debug("IntegerDataVector was successfully created");
        return new IntegerDataVector(size);
    }

    public static IntegerDataVector createWithData(int... inputData) {
        if (inputData == null || inputData.length == 0) {
            throw new IntegerDataVectorException("Trying create vector without data");
        }
        return new IntegerDataVector(inputData);
    }


    public int getSize(){
        return array.length;
    }

    public int[] getFullDataVector() {
        return array.clone();
    }

    public boolean contains(int element) {
        for (int value : array) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }

    public void setElement(int index, int value){
        log.debug("Setting element at index {} to value {}", index, value);
        if (index < 0 || index >= array.length) {
            throw new IntegerDataVectorException("Index out of range");
        }
        array[index] = value;
        log.debug("Successfully updated element at index {}", index);
    }

    public int getElementByIndex(int index) {
        log.debug("Looking for element by index: {}", index);
        if (index < 0 || index >= array.length) {
            throw new IntegerDataVectorException("Index out of range");
        }
        log.debug("Success! Element: {}", array[index]);
        return array[index];
    }

    public int getIndexOfElement(int element) {
        log.debug("Looking for element {} in vector", element);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                log.debug("Success! Index of element: {}", i);
                return i;
            }
        }
        log.debug("Element {} not found", element);
        //old thing from pascal; -1 => el not found
        return -1;
    }


    @Override
    public String toString() {
        log.debug("Converting vector to string representation");

        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder(array.length * 11 + 2);
        sb.append('[');

        sb.append(array[0]);

        for (int i = 1; i < array.length; i++) {
            sb.append(", ").append(array[i]);
        }

        sb.append(']');
        return sb.toString();
    }

    @Override
    public int hashCode(){
        log.debug("Getting vector hash");
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntegerDataVector that = (IntegerDataVector) obj;
        return Arrays.equals(this.array, that.array);
    }
}
