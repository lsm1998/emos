package com.lsm1998.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("tb_user")
@Data
public class User extends BaseModel
{
    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private Integer status;

    private String salt;
}
