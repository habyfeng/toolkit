package com.albert.toolkit.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("film")
public class Film {

    @TableField("film_id")
    private int filmId;

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
