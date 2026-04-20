package com.example.demo.Demo1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/Session")
@RestController
public class SessionController {
    //传统获取 Cookie
    @RequestMapping("/getC1")
    public String getCookie1(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).forEach(ck -> System.out.println(ck.getName() + " : " + ck.getValue()));
        }
        return "成功获取 Cookie";
    }

    //简洁获取 Cookie
    @RequestMapping("/getC2")
    public String getCookie2(@CookieValue("name") String name) {
        return "获取Cookie中 name 的值 " + name;
    }

    //设置 Session
    @RequestMapping("/setS")
    public String setSession(HttpServletRequest request) {
        //获取一个Session，没有就创建一个
        HttpSession session = request.getSession();
        session.setAttribute("name", "zhangsan");
        return "设置 Session 成功";
    }

    //获取 Session 传统方式
    @RequestMapping("/getS1")
    public String getSession1(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session =  request.getSession();
        String name = (String) session.getAttribute("name");
        return "从 Session 中获取 name : " + name;
    }

    //获取 Session2
    @RequestMapping("/getS2")
    public String getSession2(HttpSession request) {
        String name = (String) request.getAttribute("name");
        return "使用第二种方式 获取 Session 中的 name ：" + name;
    }

    //简洁获取 Session
    @RequestMapping("/getS3")
    public String getSession3(@SessionAttribute("name") String name) {
        return "简洁获取 Session 中的 name: " + name;
    }


    //获取 Header
    @RequestMapping("/getHeader1")
    public String getHeader1(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return "获取 Header 中的 userAgent: " + userAgent;
    }

    //简洁获取 Header
    @RequestMapping("getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent) {
        return "获取 Header 中的 User-Agent：" + userAgent;
    }
}
