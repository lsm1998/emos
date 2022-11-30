package com.lsm1998.football.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@TableName("tb_betting")
@Data
public class Betting extends BaseModel
{
    private Long userId;

    private String content;

    private Integer version;
}
