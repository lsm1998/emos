package com.lsm1998.common.threadLocal;

import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@Data
public class LoginUser extends BaseModel
{
    private String username;

    private String nickname;
}
