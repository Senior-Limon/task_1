package org.example.parsers;

import java.util.List;
public interface DataVectorParser {
    List<Integer> parseIntegers(String filePath) throws Exception;
}