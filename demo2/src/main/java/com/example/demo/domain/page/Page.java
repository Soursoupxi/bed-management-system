package com.example.demo.domain.page;
import lombok.Data;
//前端请求后端需要提供的参数
//1——当前页pageNum
//2——每页总行数pageSize
//后端响应到前端的结果ResultDTO
//1——总页数pages
//2——总行数total
@Data
public class Page {
    //当前页
    int pageNum;
    //每页总行数
    int pageSize;
    //总页数
    int pages;
    //总行数
    long total;
}
