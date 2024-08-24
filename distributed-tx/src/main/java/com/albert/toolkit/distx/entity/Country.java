package com.albert.toolkit.distx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("country")
public class Country {
    @TableField("Code")
    private String code;

    @TableField("Name")
    private String name;

    @TableField("surfaceArea")
    private String surfaceArea;

    @TableField("Population")
    private String population;
}
