package com.albert.toolkit.distx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city")
public class City {
    @TableField("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("CountryCode")
    private String countryCode;
}
