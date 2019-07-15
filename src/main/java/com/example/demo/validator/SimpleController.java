package com.example.demo.validator;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SimpleController {
    @PostMapping("/validation/user")
    public String register(@Validated(value = {GroupB.class}) @RequestBody UserInfoDto userInfoIDto, BindingResult result){
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String field = fieldError.getField();
            String msg = fieldError.getDefaultMessage();

            return field + ":" + msg;
        }
        System.out.println("开始注册用户...");

        return "success";
    }
}
