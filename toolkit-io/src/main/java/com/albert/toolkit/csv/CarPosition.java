package com.albert.toolkit.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CarPosition {
    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private int price;
}
