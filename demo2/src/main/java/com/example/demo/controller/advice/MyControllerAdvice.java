package com.example.demo.controller.advice;
import com.example.demo.domain.dto.ResultDTO;
import com.example.demo.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(MyException.class)
    public ResultDTO myException(MyException e) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(500);
        resultDTO.setMessage(e.getMessage());
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResultDTO myException(SQLIntegrityConstraintViolationException e) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(500);
        resultDTO.setSuccess(false);
        resultDTO.setMessage(e.getMessage());
        if (e.getMessage().indexOf("FOREIGN KEY") != -1) {
            resultDTO.setMessage("该楼层有房间，所以无法删除");
            return resultDTO;
        }
        return resultDTO;
    }

}
