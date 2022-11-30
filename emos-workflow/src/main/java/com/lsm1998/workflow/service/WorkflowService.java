package com.lsm1998.workflow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.workflow.model.Workflow;

public interface WorkflowService
{
    Long createWorkflow(Workflow workflow);

    boolean updateWorkflow(Workflow workflow);

    boolean deleteWorkflow(Long id);

    Page<Workflow> pageList(Workflow workflow, Page<Workflow> page);
}
