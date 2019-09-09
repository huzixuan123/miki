package cn.miki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("ping")
    public String ping(){
        throw new RuntimeException("服务异常测试");
    }
}
