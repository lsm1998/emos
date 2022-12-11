package com.lsm1998.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@TableName("tb_auth")
@Data
public class Auth extends BaseModel
{
    private String name;

    private String key;
}
