package com.example.demo.services.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.example.demo.domain.po.Room;
import com.example.demo.domain.vo.RoomVO;
import com.example.demo.exception.MyException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomServicesImpl implements RoomServices {

    @Autowired
    RoomMapper roomMapper;


    @Override
    public List<RoomVO> getAll() {
        List<RoomVO> all = roomMapper.getAll();
      if(  CollUtil.isEmpty(all)){
          throw new MyException("没有数据");
      }


        return all;
    }

    @Override
    public RoomVO get(int id) {
        RoomVO roomVO = roomMapper.get(id);
        if(  BeanUtil.isEmpty(roomVO)){
            throw new MyException("没有数据");
        }
        return roomVO;
    }

    @Override
    public void add(Room room) {
        int add = roomMapper.add(room);
        if(  add <= 0){
            throw new MyException("添加没有影响任何行");
        }

    }

    @Override
    public void edit(Room room) {
        int edit = roomMapper.edit(room);
        if(  edit <= 0){
            throw new MyException("没有任何行可以修改");
        }

    }

    @Override
    public void del(int id) {
        int del = roomMapper.del(id);
        if(  del <= 0){
            throw new MyException("没有任何行可以删除");
        }
    }
}
