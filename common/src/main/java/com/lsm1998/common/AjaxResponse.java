package com.lsm1998.common;

import java.util.HashMap;

import static com.lsm1998.common.constant.code.*;

public class AjaxResponse extends HashMap<String, Object>
{
    public AjaxResponse(int initialCapacity)
    {
        super(initialCapacity);
    }

    public static AjaxResponse of(int code, String message)
    {
        return AjaxResponse.of(code, message, null);
    }

    public static AjaxResponse of(int code, String message, Object data)
    {
        AjaxResponse response = new AjaxResponse(4);
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public static AjaxResponse fail()
    {
        return AjaxResponse.of(CODE_SERVER_ERROR, MESSAGE_SERVER_ERROR, null);
    }

    public static AjaxResponse success()
    {
        return AjaxResponse.of(CODE_OK, MESSAGE_OK, null);
    }

    public static AjaxResponse success(Object data)
    {
        return AjaxResponse.of(CODE_OK, MESSAGE_OK, data);
    }
}
