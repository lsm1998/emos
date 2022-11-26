package com.lsm1998.workflow.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.enums.WorkflowType;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@Data
@TableName("tb_workflow")
public class Workflow extends BaseModel
{
    private String name;

    private WorkflowType type;
}
