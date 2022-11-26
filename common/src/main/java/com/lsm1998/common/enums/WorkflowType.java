package com.lsm1998.common.enums;

public enum WorkflowType
{
    COMMON(1);

    private final Integer value;

    WorkflowType(Integer value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return value;
    }
}
