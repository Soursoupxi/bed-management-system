package com.example.demo.services;

import com.example.demo.domain.po.Floor;
import com.example.demo.domain.query.FloorQuery;
import com.example.demo.domain.vo.FloorVO;

import java.util.List;

public interface FloorServices {

    List<FloorVO> getAll();
    FloorVO get(int id);
    void add(Floor floor);
    void edit(Floor floor);
    void del(int id);


    //组合模糊查询
    List<FloorVO> getBySearch(FloorQuery floorQuery);
}
