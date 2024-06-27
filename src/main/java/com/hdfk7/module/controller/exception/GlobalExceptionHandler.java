package com.hdfk7.module.controller.exception;

import cn.hutool.json.JSONUtil;
import com.hdfk7.module.aspect.LogAspect;
import com.hdfk7.proto.base.exception.BaseException;
import com.hdfk7.proto.base.result.Result;
import com.hdfk7.proto.base.result.ResultCode;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final LogAspect logAspect;

    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        String msg = ResultCode.SYS_ERROR.getMsg();
        int code = ResultCode.SYS_ERROR.getCode();
        Object errorData = null;
        if (e instanceof BindException) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors;
            errors = ((BindException) e).getBindingResult().getFieldErrors();
            if (e instanceof MethodArgumentNotValidException) {
                errors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            }
            for (int i = 0; i < errors.size(); i++) {
                FieldError error = errors.get(i);
                errorMsg.append("[").append(error.getField()).append("]").append(error.getDefaultMessage()).append(i == errors.size() - 1 ? "" : ",");
            }
            msg = errorMsg.toString();
        }
        if (e instanceof ConstraintViolationException
                || e instanceof HttpRequestMethodNotSupportedException
                || e instanceof NoResourceFoundException) {
            msg = e.getMessage();
        }
        if (e instanceof BaseException) {
            msg = e.getMessage();
            code = ((BaseException) e).code.getCode();
            errorData = ((BaseException) e).errorData;
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String method = attributes == null ? "-" : attributes.getRequest().getMethod();
        String url = attributes == null ? "-" : attributes.getRequest().getRequestURL().toString();
        log.error("{}|{}|{}|{}", method, url, msg, JSONUtil.toJsonStr(errorData), e);
        Result<Object> result = ResultCode.SYS_ERROR.bindResult(errorData).bindMsg(msg).bindCode(code);
        logAspect.finishTask(result);
        return result;
    }
}
