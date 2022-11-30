package com.lsm1998.football.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_operation")
@Data
public class Operation
{
    private Integer userId;

    private String operation;
}
