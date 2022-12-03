package com.lsm1998.api.config;

import com.lsm1998.common.exception.EmosException;
import com.lsm1998.common.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice
{
    private static final String errorMsgFormat = "{\"error\":\"%s\"}";

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e)
    {
        if (e instanceof MethodArgumentNotValidException) // 后端验证失败产生的异常
        {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return String.format(errorMsgFormat, Objects.requireNonNull(exception.getBindingResult().getFieldError())
                    .getDefaultMessage());
        } else if (e instanceof EmosException) // 业务异常
        {
            log.error("执行异常", e);
            EmosException exception = (EmosException) e;
            return String.format(errorMsgFormat, exception.getMessage());
        } else // 其他异常
        {
            log.error("执行异常", e);
            return String.format(errorMsgFormat, "未知错误");
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public String unLoginHandler(Exception e)
    {
        return String.format(errorMsgFormat, e.getMessage());
    }
}