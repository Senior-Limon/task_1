package org.example.services.impl;

import org.example.customArray.IntegerDataVector;
import org.example.customException.IntegerDataVectorException;
import org.example.services.interfaces.IntegerDataVectorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class IntegerDataVectorServiceImpl implements IntegerDataVectorService {
    private static final Logger log = LogManager.getLogger(IntegerDataVectorServiceImpl.class);

    @Override
    public int findMaxElement(IntegerDataVector vector){
        if (vector == null || vector.getSize() == 0) {
            throw new IntegerDataVectorException("No max element in empty vector");
        }
        int max = vector.getElementByIndex(0);
        int lengthOfVector = vector.getSize();
        for (int i = 1; i < lengthOfVector; i++) {
            max = Math.max(max, vector.getElementByIndex(i));
        }
        return max;
    }

    @Override
    public int findMinElement(IntegerDataVector vector) {
        if (vector == null || vector.getSize() == 0) {
            throw new IntegerDataVectorException("No min element in empty vector");
        }
        int min = vector.getElementByIndex(0);
        int lengthOfVector = vector.getSize();
        for (int i = 1; i < lengthOfVector; i++) {
            min = Math.min(min, vector.getElementByIndex(i));
        }
        return min;
    }

    @Override
    public  int sum(IntegerDataVector vector){
        if (vector == null || vector.getSize() == 0) {
            throw new IntegerDataVectorException("Cannot calculate sum for an empty vector");
        }
        int sum = 0;
        int lengthOfVector = vector.getSize();
        for (int i = 0; i < lengthOfVector; i++) {
            sum += vector.getElementByIndex(i);
        }
        return sum;
    }

    @Override
    public IntegerDataVector qSort(IntegerDataVector vector) {
        if (vector == null) {
            throw new IntegerDataVectorException("Vector cannot be null");
        }

        if (vector.getSize() == 0) {
            return IntegerDataVector.createBySize(0);
        }
        // IntegerDataVector => array; array => ArrayList
        int[] vectorArray = vector.getFullDataVector();
        ArrayList<Integer> list = new ArrayList<>();
        for (int el : vectorArray) {
            list.add(el);
        }

        ArrayList<Integer> sortedArray = qSortHelper(list);

        // Sorted ArrayList => array; array => IntegerDataVector; return sorted IntegerDataVector
        int[] result = new int[sortedArray.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = sortedArray.get(i);
        }
        return IntegerDataVector.createWithData(result);
    }

    @Override
    public IntegerDataVector bubbleSort(IntegerDataVector vector) {
        if (vector == null) {
            throw new IntegerDataVectorException("Vector cannot be null");
        }

        if (vector.getSize() == 0) {
            return IntegerDataVector.createBySize(0);
        }

        IntegerDataVector sortedVector = IntegerDataVector.createWithData(vector.getFullDataVector());
        int length = sortedVector.getSize();

        for (int i = 0; i < length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                int current = sortedVector.getElementByIndex(j);
                int next = sortedVector.getElementByIndex(j + 1);

                if (current > next) {
                    sortedVector.setElement(j, next);
                    sortedVector.setElement(j + 1, current);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return sortedVector;
    }


    private ArrayList<Integer> qSortHelper(ArrayList<Integer> arr) {

        if (arr.size() < 2) {
            return new ArrayList<>(arr);
        }
        int opornEl = arr.get(0);

        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> bigger = new ArrayList<>();

        for (int i = 1; i < arr.size(); i++) {
            int el = arr.get(i);
            if (el > opornEl) {
                bigger.add(el);
            }
            else {
                smaller.add(el);
            }
        }

        ArrayList<Integer> result = qSortHelper(smaller);
        result.add(opornEl);
        result.addAll(qSortHelper(bigger));
        return result;
    }
}
