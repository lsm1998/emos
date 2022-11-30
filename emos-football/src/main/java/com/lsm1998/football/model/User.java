package com.lsm1998.football.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@TableName("tb_user")
@Data
public class User extends BaseModel
{
    private String username;

    private String password;

    private String realName;
}
