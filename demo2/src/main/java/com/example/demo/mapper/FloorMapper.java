package com.example.demo.mapper;
import com.example.demo.domain.po.Floor;
import com.example.demo.domain.query.FloorQuery;
import com.example.demo.domain.vo.FloorVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FloorMapper {
   List<FloorVO> getAll();
   FloorVO get(int id);
   int add(Floor floor);
   int edit(Floor floor);
   int del(int id);


   //组合模糊查询
   List<FloorVO> getBySearch(FloorQuery floorQuery);

}
