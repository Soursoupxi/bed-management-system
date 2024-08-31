package com.example.demo.services;

import com.example.demo.domain.po.Room;
import com.example.demo.domain.vo.RoomVO;

import java.util.List;

public interface RoomServices {
    List<RoomVO> getAll();
    RoomVO get(int id);
    void add(Room room);
    void edit(Room room);
    void del(int id);
}
