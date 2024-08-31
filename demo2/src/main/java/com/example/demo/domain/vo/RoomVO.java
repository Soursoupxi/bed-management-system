package com.example.demo.domain.vo;

import com.example.demo.domain.po.Floor;
import lombok.Data;

@Data
public class RoomVO {
    int id;
    String name;
    int floor_id;
    //多一个外键
    FloorVO floorVO;
}
