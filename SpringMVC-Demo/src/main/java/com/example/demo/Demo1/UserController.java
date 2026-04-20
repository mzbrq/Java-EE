package com.example.demo.Demo1;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public Boolean login(String userName, String passWord, HttpSession session) {
        //判断 账号 和 密码 合法性
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(passWord)) {
            return false;
        }

        //校验登录账号和密码是否正确
        //常量前面，防止变量为null
        if ("admin".equals(userName) && "admin".equals(passWord)) {
            //账号 和 密码 正确，设置 Session
            session.setAttribute("userName", userName);
            return true;
        }

        return false;
    }

    @RequestMapping("/index")
    public String getUserName(@SessionAttribute("userName") String userName) {
        return userName;
    }
}
