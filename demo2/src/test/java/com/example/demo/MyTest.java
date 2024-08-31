package com.example.demo;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.demo.domain.po.User;
import com.example.demo.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MyTest {


    //生成令牌
    @Test
    public void fun1() {
        // JWT头部分信息【Header】
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // 载核【Payload】
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "admin");

        // 声明Token失效时间【日历类】
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 24*60*60);// 一天

        String token = Jwts.builder()
                .setHeader(header)// 设置Header
                .setClaims(payload) // 设置载核
                .setExpiration(instance.getTime())// 设置生效时间
                //这里的私钥信息就是一个简单的字符串"secret"
                .signWith(SignatureAlgorithm.HS256,"hzj".getBytes()) // 签名,这里采用私钥进行签名,不要泄露了自己的私钥信息
                .compact(); // 压缩生成xxx.xxx.xxx

        System.out.println("token:"+token);


    }


    //解析
    @Test
    public void fun2(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJleHAiOjE3MTk2MjkzMTh9.x_Oo0gT5lJtBItdtTgWaQmxq7dtFRwf4VveAuaOrE_c";
        JwsHeader jwsHeader = Jwts
                .parser()
                .setSigningKey("hzj".getBytes())
                .parseClaimsJws(token)
                .getHeader();
        //返回的结果为JSON，JWT的每个部分都是JSON
        System.out.println(jwsHeader); // {typ=JWT, alg=HS256}
        //可以直接采用get方法获取JSON中的属性信息
        System.out.println("typ:" + jwsHeader.get("typ"));
        System.out.println("alg:" + jwsHeader.get("alg"));

        // 解析载荷Payload
        Claims claims = Jwts
                .parser()
                .setSigningKey("hzj".getBytes())
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims);
        System.out.println("name:" + claims.get("name"));

        //解析秘钥Signature
        //getSignature
        String signature = Jwts
                .parser()
                .setSigningKey("hzj".getBytes())
                .parseClaimsJws(token)
                .getSignature();
        System.out.println(signature);

    }

    @Test
    public  void fun3(){
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("name","xxxxxx");
        //生成令牌
        String token = JWTUtil.createToken(payload, "hzj".getBytes());
        System.out.println(token);
    }
    @Test
    public void fun4(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoieHh4eHh4In0.A1lQ7Q-Tz5uRZn34UWG5XWDZ3dSbWfw8nxZQKvUQJfc";
        //先验证
        if(JWTUtil.verify(token,"hzj".getBytes())){
            //再解析
            JWT jwt = JWTUtil.parseToken(token);
            Object name = jwt.getPayload().getClaim("name");
            System.out.println(name);
        }
        else{
            System.out.println("错误...");
        }
    }



    @Autowired
    UserMapper userMapper;
    @Test
    public void mapper(){
        User admin = userMapper.getByNameAndPassword("admin", "123456");
        System.out.println(admin);
    }
}
