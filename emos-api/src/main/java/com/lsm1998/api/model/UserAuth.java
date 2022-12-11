package com.lsm1998.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@TableName("tb_user_auth")
@Data
public class UserAuth extends BaseModel
{
    private Long userId;

    private Long authId;
}
