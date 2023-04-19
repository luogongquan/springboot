package com.example.springboot.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


/**
 * @author：罗功权
 * @date：2023/3/22 11:22
 **/
@Component
@Aspect
public class RecordAspect {

    @Pointcut("@annotation(com.example.springboot.study.aspect.RecordOperate)")
    public void piointCut(){

    }

    @Around("piointCut()")
    public Object invote(ProceedingJoinPoint point) throws Throwable {
        MethodSignature proceed = (MethodSignature)point.getSignature();
        //MethodSignature proceed = (MethodSignature) point.proceed();
        RecordOperate annotation = proceed.getMethod().getAnnotation(RecordOperate.class);

        Class<? extends Covert> covert = annotation.covert();
        Covert covert1 = covert.newInstance();
        RecordOperateLog covert2 = covert1.covert(point.getArgs()[0]);
        covert2.setDesc(annotation.desc());
        System.out.println(JSONObject.valueToString(covert2));
        return point.proceed();
    }
}
