package com.example.demo.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.demo.common.TreadLocalUtil;
import com.example.demo.exception.MyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //处理请求之前的事情
    //返回true则放行，否则不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("正在拦截的地址开始：" + request.getRequestURI());
        String token = request.getHeader("Token");
        System.out.println("token:" + token);
        //提前设定错误的响应代码
        response.setStatus(444);
        //判断前端是否携带token
        if (token == null) {

            throw new MyException("前端没有携带token");
        }

        //验证前端携带的token
        try{
            if (!JWTUtil.verify(token, "123456".getBytes())) {
                throw new MyException("前端携带的token无效");
            }
        }
        catch (Throwable e){
            throw new MyException("前端携带的token无效");
        }


        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        String tokenRedis = stringValueOperations.get("token");
        if(tokenRedis==null){
            throw new MyException("Redis里面的没有token");
        }

        //验证前端携带的token
        try{
            if (!JWTUtil.verify(tokenRedis, "123456".getBytes())) {
                throw new MyException("Redis里面的token无效");
            }
        }
        catch (Throwable e){
            throw new MyException("Redis里面的token无效");
        }

        //判断令牌的新旧问题
        if(!token.equals(tokenRedis)){
            throw new MyException("前端的token是旧的");
        }

        //设置正确的响应
        response.setStatus(200);
        //获取当前用户信息
        JWT jwt = JWTUtil.parseToken(token);
        String name =(String) jwt.getPayload("name");
        //name需要共享，但是线程私有
        TreadLocalUtil.set(name);
        return true;
    }

    //处理响应之前还没有渲染到前端的事情
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //处理渲染到前端之后处理的事情
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理ThreadLocal
        TreadLocalUtil.remove();
        System.out.println("正在拦截的地址结束：" + request.getRequestURI());
    }
}
