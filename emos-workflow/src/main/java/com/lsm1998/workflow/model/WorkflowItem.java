package com.lsm1998.workflow.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@Data
@TableName("tb_Workflow_item")
public class WorkflowItem extends BaseModel
{
    private Long workflowId;

    private Integer sort;
}
