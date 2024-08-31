package com.example.demo.services.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.example.demo.domain.po.User;
import com.example.demo.exception.MyException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public String login(String name, String password) {

        //判断用户账号和密码是否成立
        User user = userMapper.getByNameAndPassword(name, password);
        if(BeanUtil.isEmpty(user) ){
            throw  new MyException("登录失败，用户名或者密码错误");
        }
        HashMap<String, Object> payload = new HashMap<>();
        //添加用户账号
        payload.put("name",user.getName());
        //添加一个时间戳【当前的毫秒数】
        payload.put("time",System.currentTimeMillis());

        //生成token
        String token = JWTUtil.createToken(payload, "123456".getBytes());

        //把token写入到Redis当中
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        //存储数据到Redis的时候必须保持与token一样的失效时间
        stringValueOperations.set("token",token,30, TimeUnit.MINUTES);


        return token;

    }

    @Override
    public void logout() {

        //删除Redis的token
        stringRedisTemplate.delete("token");
    }
}
