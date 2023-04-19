package com.example.springboot.study.aspect;

import org.springframework.stereotype.Service;

/**
 * @author：罗功权
 * @date：2023/3/22 12:04
 **/
@Service
public class RecordService {
    @RecordOperate(desc = "保存功能",covert = SaveCovert.class)
    public void save(SaveDo saveDo){

        System.out.println("保存功能：orderID："+saveDo.getId());
    }
    @RecordOperate(desc = "更新操作",covert = UpdateCovert.class)
    public void update(UpdateDO updateDO){

        System.out.println("更新功能：orderID："+updateDO.getId());
    }
}
