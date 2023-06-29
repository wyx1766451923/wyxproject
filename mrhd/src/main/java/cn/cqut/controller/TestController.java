package cn.cqut.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String test1(){
        System.out.println("test1被执行");
        return "test1测试";
    }
    @RequestMapping("/test2")
    public String test2(){
        System.out.println("test2被执行");
        return "test2测试";
    }
}
