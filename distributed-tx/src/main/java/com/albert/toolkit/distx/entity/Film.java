package com.albert.toolkit.distx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("film")
public class Film {

    @TableField("film_id")
    private Integer filmId;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    /**
     * YYYY
     */
    @TableField("release_year")
    private String releaseYear;

}
