package com.example.demo.mapper;
import com.example.demo.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    User getByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
