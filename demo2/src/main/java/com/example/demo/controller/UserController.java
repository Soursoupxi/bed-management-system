package com.example.demo.controller;

import com.example.demo.domain.dto.ResultDTO;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices userServices;

    @PostMapping("login")
    public ResultDTO login(@RequestParam("name") String name, @RequestParam("password") String password) {
        String token = userServices.login(name, password);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("登录成功");
        resultDTO.setData(token);
        return resultDTO;
    }

    @GetMapping("logout")
    public ResultDTO logout() {
        userServices.logout();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("登出成功");
        return resultDTO;
    }


}
