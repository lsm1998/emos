package com.lsm1998.workflow.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsm1998.common.enums.ApprovalStatus;
import com.lsm1998.common.enums.WorkflowType;
import com.lsm1998.common.model.BaseModel;
import lombok.Data;

@Data
@TableName("tb_workflow")
public class Workflow extends BaseModel
{
    // 工作流程名称
    private String name;

    // 工作流程类型
    private WorkflowType type;

    // 当前审核人
    private String approval;

    // 当前流程项
    private Long currentItem;

    // 最终流程项
    private Long endItem;

    // 工作流程状态
    private ApprovalStatus status;
}

