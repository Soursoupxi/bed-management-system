package com.example.demo.controller;
import com.example.demo.domain.dto.ResultDTO;
import com.example.demo.domain.po.Room;
import com.example.demo.domain.vo.RoomVO;
import com.example.demo.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomServices roomServices;

    @GetMapping("getAll")
    public Object getAll() {

        List<RoomVO> all = roomServices.getAll();
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setMessage("查询成功");
        resultDTO.setData(all);
        return resultDTO;
    }

    @GetMapping("get")
    public Object get(int id) {
        RoomVO roomVO = roomServices.get(id);
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setMessage("查询成功");
        resultDTO.setData(roomVO);
        return resultDTO;
    }


    @PostMapping("add")
    public Object add(Room room) {

        roomServices.add(room);
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setMessage("添加成功");
        return resultDTO;
    }

    @PostMapping("edit")
    public Object edit(Room room) {
        roomServices.edit(room);

        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setMessage("修改成功");
        return resultDTO;
    }

    @GetMapping("del")
    public Object del(int id) {
        roomServices.del(id);
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setMessage("删除成功");
        return resultDTO;
    }


}
