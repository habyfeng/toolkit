package com.albert.toolkit.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * bean属性映射到 CSV列
 */
@Data
public class Car {
    @CsvBindByName
    private int id;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private int price;
}
