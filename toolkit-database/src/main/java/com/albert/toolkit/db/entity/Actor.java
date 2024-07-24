package com.albert.toolkit.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("actor")
public class Actor {
    @TableField("actor_id")
    private int actorId;

    @TableField("first_name")
    private String firstName;


    @TableField("last_name")
    private String lastName;

}
