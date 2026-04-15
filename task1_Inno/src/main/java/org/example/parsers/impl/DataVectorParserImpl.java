package org.example.parsers.impl;

import org.example.parsers.DataVectorParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//parse file where new line = new digit

public class DataVectorParserImpl implements DataVectorParser {
    private static final Logger log = LogManager.getLogger(DataVectorParserImpl.class);

    @Override
    public List<Integer> parseIntegers(String filePath) throws IOException {
        List<Integer> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.strip();
                if (line.isBlank()) continue;

                try {
                    result.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    log.warn("Строка {}: пропущено неверное значение '{}'", lineNum, line);
                }
            }
        }

        log.info("Загружено {} чисел из {}", result.size(), filePath);
        return result;
    }
}