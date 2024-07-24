package com.albert.toolkit.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * CSV文件工具类
 *
 * @author hoyo
 * @since 2024-07-21
 */
public class CsvUtil {

    /**
     * @param fileName  csv文件路径
     * @param separator cdv分隔符
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public static List<String[]> readCsv(String fileName, char separator) throws IOException, CsvException {
        CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
        Path csvPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(br).withCSVParser(parser).build()) {
            List<String[]> rows = csvReader.readAll();

            return rows;
        }

    }

    /**
     * 读取CSV并根据属性名称映射为java bean
     *
     * @param fileName csv文件路径
     * @param separator csv分隔符
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public static List<Car> readToBeanByName(String fileName, char separator) throws IOException, CsvException {
        HeaderColumnNameMappingStrategy<Car> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Car.class);
        Path csvPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            CsvToBean<Car> csvToBean = new CsvToBeanBuilder<Car>(br)
                    .withType(Car.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();
            List<Car> cars = csvToBean.parse();

            return cars;
        }
    }

    /**
     * 读取CSV并根据属性位置映射为java bean
     *
     * @param fileName csv文件路径
     * @param separator csv分隔符
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public static List<CarPosition> readToBeanByIndex(String fileName, char separator) throws IOException, CsvException {
        ColumnPositionMappingStrategy<CarPosition> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(CarPosition.class);
        String[] fields = {"id", "name", "price"};
        strategy.setColumnMapping(fields);
        Path csvPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            CsvToBean<CarPosition> csvToBean = new CsvToBeanBuilder<CarPosition>(br)
                    .withType(CarPosition.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();
            List<CarPosition> cars = csvToBean.parse();

            return cars;
        }
    }

}
