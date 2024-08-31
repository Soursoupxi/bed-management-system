package com.example.demo.controller;
import cn.hutool.core.thread.ThreadUtil;
import com.example.demo.common.TreadLocalUtil;
import com.example.demo.domain.dto.ResultDTO;
import com.example.demo.domain.po.Floor;
import com.example.demo.domain.query.FloorQuery;
import com.example.demo.domain.vo.FloorVO;
import com.example.demo.services.FloorServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {
    @Autowired
    FloorServices floorServices;

    //localhost:8080/floor/getAll?pageNum=1&pageSize=5
    //请求时携带ResultDTO参数准备补充分页信息
    @GetMapping("getAll")
    public ResultDTO getAll(ResultDTO resultDTO) {

        //打印当前用户
        System.out.println("getAll"+TreadLocalUtil.get());


        //插件开始分页和计算
        //pageNum
        //pageSize
        PageHelper.startPage(resultDTO.getPageNum(),resultDTO.getPageSize());
        //在startPage下面的第一句话消费分页信息
        List<FloorVO> floorVOS = floorServices.getAll();
        //把结果集转换成PageInfo
        PageInfo<FloorVO> pageInfo = new PageInfo<>(floorVOS);
        //清除分页
        PageHelper.clearPage();
        //补齐总页数
        resultDTO.setPages(pageInfo.getPages());
        //补齐总行数
        resultDTO.setTotal(pageInfo.getTotal());
        resultDTO.setData(floorVOS);
        resultDTO.setMessage("查询成功");
        return resultDTO;
    }

    @GetMapping("get")
    public ResultDTO get(int id) {
        ResultDTO resultDTO = new ResultDTO();
        FloorVO floorVO = floorServices.get(id);
        resultDTO.setData(floorVO);
        resultDTO.setMessage("查询成功");
        return resultDTO;
    }


    @GetMapping("getBySearch")
    public ResultDTO getAll(ResultDTO resultDTO, FloorQuery floorQuery) {
        //插件开始分页和计算
        //pageNum
        //pageSize
        PageHelper.startPage(resultDTO.getPageNum(),resultDTO.getPageSize());
        //在startPage下面的第一句话消费分页信息
        List<FloorVO> floorVOS = floorServices.getBySearch(floorQuery);
        //把结果集转换成PageInfo
        PageInfo<FloorVO> pageInfo = new PageInfo<>(floorVOS);
        //清除分页
        PageHelper.clearPage();
        //补齐总页数
        resultDTO.setPages(pageInfo.getPages());
        //补齐总行数
        resultDTO.setTotal(pageInfo.getTotal());
        resultDTO.setData(floorVOS);
        resultDTO.setMessage("查询成功");
        return resultDTO;
    }



    @PostMapping("add")
    public ResultDTO add(@RequestBody Floor floor) {
        floorServices.add(floor);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("添加成功");
        return resultDTO;
    }

    @PostMapping("edit")
    public ResultDTO edit(@RequestBody Floor floor) {
        floorServices.edit(floor);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("修改成功");
        return resultDTO;
    }

    @GetMapping("del")
    public ResultDTO del(int id) {
        floorServices.del(id);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("删除成功");
        return resultDTO;
    }
}
