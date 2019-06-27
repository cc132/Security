package com.example.demo.d1.controller;

import com.example.demo.d1.entity.UserDO;
import com.example.demo.d1.service.UserService;
import lombok.AllArgsConstructor;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/failure")
    public String failure(@AuthenticationPrincipal Principal principal, Model model) {
    	return "failure";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(UserDO userDO){
        // 此处省略校验逻辑
        userService.insert(userDO);
        return "redirect:register?success";
    }

}
