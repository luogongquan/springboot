package com.example.springboot.study.word;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.excel.export.base.ExportCommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 *@ClassNameExportWordController
 *@Description描述:
 *
 *@Date2021/6/29 15:29
 *@Version1.0
 **/
@RestController
@RequestMapping("/export/")
public class ExportWordController extends ExportCommonService {

    /**
     * easypoi导出word
     */
    @GetMapping("word/demo1")
    public void easyExportWord1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String templatePath = "template/demo1.docx"; //模板路径

        //简单渲染文本
        params.put("title1","2021年暑期放假通知");
        params.put("body","经市教委研究决定，2021年暑期假日，于7月12日开始至9月10日结束。祝大家，节日快乐！");
        params.put("date","2021-06-29");
        params.put("card","市教育局");
        params.put("title2","2021暑期值班人员表");

        //图片 读取方式: 1、字节流 2、路径
        /*ByteArrayOutputStream out = new ByteArrayOutputStream();
        ClassPathResource resource = new ClassPathResource("static/log.jpg");
        InputStream inputStream = resource.getInputStream();
        BufferedImage read = ImageIO.read(inputStream);
        ImageIO.write(read,"jpg",out);*/

        ImageEntity image = new ImageEntity();
        image.setHeight(30);
        image.setWidth(30);
        image.setUrl("static/png.png"); //url路径
        //image.setData(out.toByteArray()); //字节流读取
        //设置读取图片方式(必须)
        image.setType(ImageEntity.URL);
        //表格外添加简单图片
        params.put("img", image);

        //渲染表格
        List<Map<String, Object>> jobs = new ArrayList<>();
        Map<String, Object> job;
        for (int i = 0; i < 5; i++) {
            job = new HashMap<>();
            job.put("name", "洪不亮-" + i);
            job.put("sex", "男:" + i);
            job.put("dept", "开发部:" + i);
            //表格内循环添加图片(easypoi 4.3以后才支持,不然只能打印出ImageEntity的内存地址)
            job.put("img", image);

            jobs.add(job);
        }
        //添加
        params.put("jobs",jobs);

        String temDir="D:/mimi/"+ File.separator+"file/word/"; ;//生成临时文件存放地址
        //生成文件名
        Long time = new Date().getTime();
        // 生成的word格式
        String formatSuffix = ".docx";
        // 拼接后的文件名
        String fileName = time + formatSuffix;//文件名  带后缀
        //导出word
        WordUtil.exportWord(templatePath, temDir, fileName, params, request, response);
    }

    /**
     * easypoi导出word
     */
    @GetMapping("word/alram")
    public void alram(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String templatePath = "template/alarmMould.docx"; //模板路径
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("carCount","1000123");
        obj.put("cartboxOnlineCountCount","874");
        obj.put("onlineRate","80%");
        obj.put("tripDistanceCount","2131235423");
        obj.put("averageVehicleMileage","123123");
        HashMap<String, Object> obj2 = new HashMap<>();
        obj2.put("recordTotal","1000123");
        obj2.put("recordTotalLow","11111");
        obj2.put("recordTotalMedium","22222");
        obj2.put("recordTotalHigh","333333");
        obj2.put("handleRate","12.00%");
        obj2.put("chainAdd","23.12%");
        //简单渲染文本
        params.put("carStatDailyInfo",obj);
        params.put("alarmEventCountInfo",obj2);
        //添加

        ImageEntity image = new ImageEntity();
        image.setHeight(150);
        image.setWidth(250);
        image.setData(PieChartGenerator.generatePieChart());
        image.setType(ImageEntity.Data);
        params.put("image",image);


        String temDir="D:/mimi/"+ File.separator+"file/word/"; ;//生成临时文件存放地址
        //生成文件名
        Long time = new Date().getTime();
        // 生成的word格式
        String formatSuffix = ".docx";
        // 拼接后的文件名
        String fileName = time + formatSuffix;//文件名  带后缀
        //导出word
        WordUtil.exportWord(templatePath, temDir, fileName, params, request, response);
    }

    @GetMapping("getPath")
    public String getPath(){
        String property = System.getProperty("user.dir");
        return property;
    }
}

