package dataVector;

import org.example.customArray.IntegerDataVector;
import org.example.customException.IntegerDataVectorException;
import org.example.services.interfaces.IntegerDataVectorService;
import org.example.services.impl.IntegerDataVectorServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerDataVectorServiceTest {

    private final IntegerDataVectorService service = new IntegerDataVectorServiceImpl();

    @Test
    void findMaxElement() {
        IntegerDataVector vec = IntegerDataVector.createWithData(10, 5, 20, 1);
        assertEquals(20, service.findMaxElement(vec));
    }

    @Test
    void findMaxElement_ThrowsOnBadInput() {
        assertThrows(IntegerDataVectorException.class, () -> service.findMaxElement(null));
        assertThrows(IntegerDataVectorException.class, () -> service.findMaxElement(IntegerDataVector.createBySize(0)));
    }

    @Test
    void findMinElement() {
        IntegerDataVector vec = IntegerDataVector.createWithData(10, 5, 20, 1);
        assertEquals(1, service.findMinElement(vec));
    }

    @Test
    void findMinElement_ThrowsOnBadInput() {
        assertThrows(IntegerDataVectorException.class, () -> service.findMinElement(null));
        assertThrows(IntegerDataVectorException.class, () -> service.findMinElement(IntegerDataVector.createBySize(0)));
    }

    @Test
    void sum() {
        IntegerDataVector vec = IntegerDataVector.createWithData(1, 2, 3, 4);
        assertEquals(10, service.sum(vec));
    }

    @Test
    void sum_ThrowsOnEmpty() {
        assertThrows(IntegerDataVectorException.class, () -> service.sum(IntegerDataVector.createBySize(0)));
    }

    @Test
    void qSort_SortsCorrectly() {
        IntegerDataVector vec = IntegerDataVector.createWithData(9, 2, 7, 4, 1);
        IntegerDataVector sorted = service.qSort(vec);
        assertArrayEquals(new int[]{1, 2, 4, 7, 9}, sorted.getFullDataVector());
    }

    @Test
    void qSort_DoesNotMutateOriginal() {
        IntegerDataVector vec = IntegerDataVector.createWithData(5, 3, 8, 1);
        int[] original = vec.getFullDataVector();

        service.qSort(vec);

        assertArrayEquals(original, vec.getFullDataVector());
    }

    @Test
    void bubbleSort_SortsCorrectly() {
        IntegerDataVector vec = IntegerDataVector.createWithData(5, 3, 8, 1);
        IntegerDataVector sorted = service.bubbleSort(vec);
        assertArrayEquals(new int[]{1, 3, 5, 8}, sorted.getFullDataVector());
    }

    @Test
    void selectionSort_SortsCorrectly() {
        IntegerDataVector vec = IntegerDataVector.createWithData(5, 3, 8, 1);
        IntegerDataVector sorted = service.bubbleSort(vec);
        assertArrayEquals(new int[]{1, 3, 5, 8}, sorted.getFullDataVector());
    }

    @Test
    void sort_EmptyVector_ReturnsEmpty() {
        IntegerDataVector empty = IntegerDataVector.createBySize(0);
        assertEquals(0, service.qSort(empty).getSize());
        assertEquals(0, service.bubbleSort(empty).getSize());
        assertEquals(0, service.bubbleSort(empty).getSize());
    }

    @Test
    void sort_NullVector_Throws() {
        assertThrows(IntegerDataVectorException.class, () -> service.qSort(null));
        assertThrows(IntegerDataVectorException.class, () -> service.bubbleSort(null));
        assertThrows(IntegerDataVectorException.class, () -> service.bubbleSort(null));
    }
}