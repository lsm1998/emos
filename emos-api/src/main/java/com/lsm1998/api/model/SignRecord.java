package com.lsm1998.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

@TableName("tb_sign_record")
@Data
public class SignRecord extends BaseModel
{
    private Long user_id;

    private Date date;
}
