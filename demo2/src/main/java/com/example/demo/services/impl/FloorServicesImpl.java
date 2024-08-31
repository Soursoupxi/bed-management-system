package com.example.demo.services.impl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.example.demo.domain.po.Floor;
import com.example.demo.domain.query.FloorQuery;
import com.example.demo.domain.vo.FloorVO;
import com.example.demo.exception.MyException;
import com.example.demo.mapper.FloorMapper;
import com.example.demo.services.FloorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FloorServicesImpl implements FloorServices {


    @Autowired
    FloorMapper floorMapper;

    @Override
    public List<FloorVO> getAll() {
        List<FloorVO> floorVOS = floorMapper.getAll();
        if(CollUtil.isEmpty(floorVOS)){
            throw new MyException("没有数据");
        }
        return floorVOS;
    }

    @Override
    public FloorVO get(int id) {
        FloorVO floorVO = floorMapper.get(id);
        if(BeanUtil.isEmpty(floorVO)){
            throw new MyException("没有数据");
        }
        return floorVO ;
    }

    @Override
    public void add(Floor floor) {
        int add = floorMapper.add(floor);
        if(add<=0){
            throw new MyException("添加没有影响任何行");
        }
    }

    @Override
    public void edit(Floor floor) {
        int edit = floorMapper.edit(floor);
        if(edit<=0){
            throw new MyException("没有任何行可以修改");
        }
    }

    @Override
    public void del(int id) {
        int del = floorMapper.del(id);
        if(del<=0){
            throw new MyException("没有任何行可以删除");
        }
    }

    @Override
    public List<FloorVO> getBySearch(FloorQuery floorQuery) {

        List<FloorVO> floorVOS = floorMapper.getBySearch(floorQuery);
        if(CollUtil.isEmpty(floorVOS)){
            throw new MyException("没有数据");
        }
        return floorVOS;
    }
}
