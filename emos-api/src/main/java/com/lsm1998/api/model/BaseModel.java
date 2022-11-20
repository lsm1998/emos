package com.lsm1998.api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class BaseModel implements Serializable
{
    @TableId(type = AUTO)
    private Long id;

    private Date createdTime;

    private Date updatedTime;
}
