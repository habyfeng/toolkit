package com.albert.toolkit.distx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("actor")
public class Actor {
    @TableField("actor_id")
    private Integer actorId;

    @TableField("first_name")
    private String firstName;


    @TableField("last_name")
    private String lastName;

}
