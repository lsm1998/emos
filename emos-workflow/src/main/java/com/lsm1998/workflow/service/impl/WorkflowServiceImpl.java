package com.lsm1998.workflow.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsm1998.workflow.model.Workflow;
import com.lsm1998.workflow.service.WorkflowService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements WorkflowService
{
    @Override
    public Long createWorkflow(Workflow workflow)
    {
        return null;
    }

    @Override
    public boolean updateWorkflow(Workflow workflow)
    {
        return false;
    }

    @Override
    public boolean deleteWorkflow(Long id)
    {
        return false;
    }

    @Override
    public Page<Workflow> pageList(Workflow workflow, Page<Workflow> page)
    {
        return null;
    }
}
