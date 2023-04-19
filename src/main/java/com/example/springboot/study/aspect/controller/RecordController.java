package com.example.springboot.study.aspect.controller;

import com.example.springboot.study.aspect.BaseDo;
import com.example.springboot.study.aspect.RecordService;
import com.example.springboot.study.aspect.SaveDo;
import com.example.springboot.study.aspect.UpdateDO;
import org.aspectj.lang.annotation.Around;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：罗功权
 * @date：2023/3/22 11:24
 **/
@RestController
@RequestMapping("record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @RequestMapping("save")
    public void save(){
        SaveDo saveDo = new SaveDo();
        saveDo.setId(1);
        saveDo.setName("apple");
        recordService.save(saveDo);
        UpdateDO updateDO = new UpdateDO();
        updateDO.setId(2);
        updateDO.setName("banane");
        recordService.update(updateDO);
    }

    public static void main(String[] args) {
        BaseDo saveDo = new SaveDo();
        saveDo.setTtt(1);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(saveDo));
        System.out.println(JSONObject.valueToString(saveDo));
    }
}
