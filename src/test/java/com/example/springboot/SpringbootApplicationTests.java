package com.example.springboot;

import com.example.springboot.study.aspect.RecordService;
import com.example.springboot.study.aspect.SaveDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    RecordService recordService;
    @Test
    void contextLoads() {
        SaveDo saveDo = new SaveDo();
        saveDo.setName("zhangsan");
        saveDo.setId(22);
      //  recordService.save(saveDo);
        System.out.println(1123131);
    }

}
