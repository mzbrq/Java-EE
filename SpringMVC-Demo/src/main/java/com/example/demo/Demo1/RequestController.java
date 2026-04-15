package com.example.demo.Demo1;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {
    @RequestMapping("/hello")
    public String say() {
        return "Hello Spring MVC";
    }

    @RequestMapping("/r1")
    public String r1(String name) {
        return "接收到请求" + name;
    }

    @RequestMapping("/r2")
    public String r2(String name, Integer age) {
        return "接收到请求 name: " + name + "age: " + age;
    }

    @RequestMapping("/r3")
    public String r3(String name, int age) {
        return "接收到请求 name: " + name + "age: " + age;
    }

    @RequestMapping("/r4")
    public String r4(Student student) {
        return student.toString();
    }

    @RequestMapping("/r5")
    public String r5(@RequestParam(value = "name", required = false) String userName) {
        return "接收到参数 name: " + userName;
    }

    @RequestMapping("/r6")
    public String r6(int[] array) {
        return "接收到数组参数： " + Arrays.toString(array);
    }

    @RequestMapping("/r7")
    public String r7(@RequestParam List list) {
        return "接收到集合参数: " + list;
    }

    @RequestMapping("/r8")
    public String r8(@RequestBody Student student) {
        return "接收到 JSON参数：" + student;
    }


    /*从路径获取ID参数*/
    @RequestMapping("/r9/{articleId}")
    public String r9(@PathVariable("articleId") Integer articleId) {
        return "接收到：" + articleId;
    }


}
