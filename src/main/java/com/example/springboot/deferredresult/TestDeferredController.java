package com.example.springboot.deferredresult;/**
 * @description: TODO
 * @author: lgq
 * @date: 2023/11/10 15:14
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ClassName: TestDeferredController
 * @author: luogongquan
 * @since: 2023/11/10 15:14
 */
@RestController
@RequestMapping("/deferred")
public class TestDeferredController {


    @RequestMapping("/test")
    public DeferredResult<String> test(){
        DeferredResult<String> deferredResult = new DeferredResult<>(2000L,"请求超时");
        DeferredCache.DEFERRED_CACHE.put("aa",deferredResult);
        new Thread(()->{
            try {
                Thread.sleep(1000);
                DeferredCache.DEFERRED_CACHE.getIfPresent("aa").setResult("测试通过");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return deferredResult;
    }



    @RequestMapping("/test1")
    public String test1(){
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return "返回1";
    }


}
