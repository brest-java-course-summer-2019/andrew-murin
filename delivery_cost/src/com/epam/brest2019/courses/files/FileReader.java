package com.epam.brest2019.courses.files;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface FileReader {

    Map<Integer, BigDecimal> readData(final String filePath) throws IOException;
}
