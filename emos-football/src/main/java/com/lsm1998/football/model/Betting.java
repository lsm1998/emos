package com.lsm1998.football.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_betting")
@Data
public class Betting
{
    private Integer userId;

    private String content;

    private Integer version;
}
