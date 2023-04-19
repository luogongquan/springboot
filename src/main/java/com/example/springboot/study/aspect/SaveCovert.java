package com.example.springboot.study.aspect;

/**
 * @author：罗功权
 * @date：2023/3/22 11:55
 **/
public class SaveCovert implements Covert<SaveDo>{
    @Override
    public RecordOperateLog covert(SaveDo o) {
        RecordOperateLog recordOperateLog = new RecordOperateLog();
        recordOperateLog.setOrderId(o.getId());
        recordOperateLog.setName(o.getName());
        return recordOperateLog;
    }
}
