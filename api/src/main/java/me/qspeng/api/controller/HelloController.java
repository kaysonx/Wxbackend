package me.qspeng.api.controller;

import me.qspeng.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "hello, gradle";
    }

    @RequestMapping("user")
    public String user() {
        TestService testService = new TestService();
        return testService.testUser().toString();
    }
}
