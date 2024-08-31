package com.example.demo.domain.dto;
import com.example.demo.domain.page.Page;
import lombok.Data;
//继承Page获取分页属性
@Data
public class ResultDTO extends Page {
    //响应结果是否正确的编码
    //200正确，500错误
    int code=200;
    //响应结果是否正确
    boolean isSuccess=true;
    //响应的文本信息
    String message;
    //响应的数据
    Object data;
}
