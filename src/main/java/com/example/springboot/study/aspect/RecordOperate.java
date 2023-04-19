package com.example.springboot.study.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperate {
    String desc() default "";
    Class<? extends Covert> covert();
}
