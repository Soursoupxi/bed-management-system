package com.example.demo.mapper;
import com.example.demo.domain.po.Bed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BedMapper {
    List<Bed> getAll();
    Bed get(int id);
    int add(Bed bed);
    int edit(Bed bed);
    int del(int id);
}
