package com.lsm1998.common.enums;

public enum ApprovalStatus
{
    INIT(1);

    private final Integer value;

    ApprovalStatus(Integer value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return value;
    }
}
