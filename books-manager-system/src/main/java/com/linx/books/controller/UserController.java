package com.linx.books.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public String login(String userName, String password, HttpSession session) {
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return "账号或密码不能为空";
        }

        if (!"admin".equals(userName) || !"admin".equals(password)) {
            return "密码或账号错误";
        }
        session.setAttribute("userName", userName);
        return "";
    }


}
