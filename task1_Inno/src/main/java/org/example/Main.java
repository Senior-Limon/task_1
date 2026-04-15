package org.example;

import org.example.customArray.IntegerDataVector;
import org.example.parsers.DataVectorParser;
import org.example.parsers.impl.DataVectorParserImpl;
import org.example.services.interfaces.IntegerDataVectorService;
import org.example.services.impl.IntegerDataVectorServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = "D:\\task1_inno\\task1_Inno\\src\\main\\resources\\numsForArray.txt";


        DataVectorParser parser = new DataVectorParserImpl();
        IntegerDataVectorService service = new IntegerDataVectorServiceImpl();

        // parsing
        List<Integer> numbers;
        try {
            numbers = parser.parseIntegers(filePath);
        } catch (Exception e) {
            log.error("Error where reading file '{}': {}", filePath, e.getMessage());
            return;
        }

        if (numbers.isEmpty()) {
            log.error("File is empty!");
            return;
        }

        // List<Integer> → int[] -> DataVector
        int[] rawData = numbers.stream().mapToInt(Integer::intValue).toArray();
        IntegerDataVector vector = IntegerDataVector.createWithData(rawData);

        log.info("Vector: {}", vector);
        log.info("Max element: {}", service.findMaxElement(vector));
        log.info("Sum of elements: {}", service.sum(vector));
        log.info("Sorted vector: {}", service.qSort(vector));
    }
}