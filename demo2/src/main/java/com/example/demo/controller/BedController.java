package com.example.demo.controller;
import com.example.demo.domain.po.Bed;
import com.example.demo.services.BedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bed")
public class BedController {

    @Autowired
    BedServices bedServices;

    @GetMapping("getAll")
    public Object getAll() {

        return bedServices.getAll();
    }

    @GetMapping("get")
    public Object get(int id) {
        return bedServices.get(id);
    }

    @PostMapping("add")
    public Object add(Bed bed) {
        bedServices.add(bed);
        return "成功";
    }

    @PostMapping("edit")
    public Object edit(Bed bed) {
        bedServices.edit(bed);
        return "成功";
    }

    @GetMapping("del")
    public Object del(int id) {
        bedServices.del(id);
        return "成功";
    }
}
