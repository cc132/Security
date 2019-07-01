package com.example.demo.d1.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("permitAll")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "user/user";
    }
    
    //hasAuthority和hasRole的区别
    @GetMapping("/vip")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public String vip() {
    	return "vip 功能";
    }
}
