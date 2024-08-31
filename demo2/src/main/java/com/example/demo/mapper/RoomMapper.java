package com.example.demo.mapper;
import com.example.demo.domain.po.Room;
import com.example.demo.domain.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoomMapper {

    List<RoomVO> getAll();
    RoomVO get(int id);
    int add(Room room);
    int edit(Room room);
    int del(int id);
}
