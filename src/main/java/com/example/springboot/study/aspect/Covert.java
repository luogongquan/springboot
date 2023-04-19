package com.example.springboot.study.aspect;

/**
 * @author：罗功权
 * @date：2023/3/22 11:51
 **/
public interface Covert<PARAM> {
    RecordOperateLog covert(PARAM param);
}
