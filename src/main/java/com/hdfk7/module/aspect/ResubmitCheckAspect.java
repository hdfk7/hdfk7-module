package com.hdfk7.module.aspect;

import com.hdfk7.proto.base.annotation.ResubmitCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResubmitCheckAspect extends com.hdfk7.boot.starter.common.aspect.ResubmitCheckAspect {
    @Before("@within(resubmitCheck)")
    public void adviceClazz(JoinPoint joinPoint, ResubmitCheck resubmitCheck) {
        doAdvice(joinPoint, resubmitCheck);
    }

    @Before("@annotation(resubmitCheck)")
    public void adviceMethod(JoinPoint joinPoint, ResubmitCheck resubmitCheck) {
        doAdvice(joinPoint, resubmitCheck);
    }
}
