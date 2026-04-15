package org.example.services.interfaces;

import org.example.customArray.IntegerDataVector;

public interface IntegerDataVectorService {
    int findMaxElement(IntegerDataVector vector);
    int findMinElement(IntegerDataVector vector);
    int sum(IntegerDataVector vector);
    IntegerDataVector qSort(IntegerDataVector vector);
    IntegerDataVector bubbleSort(IntegerDataVector vector);
}
