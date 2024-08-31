package com.example.demo.services.impl;

import com.example.demo.domain.po.Bed;
import com.example.demo.mapper.BedMapper;
import com.example.demo.services.BedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BedServicesImpl implements BedServices {

    @Autowired
    BedMapper bedMapper;

    @Override
    public List<Bed> getAll() {
        return bedMapper.getAll();
    }

    @Override
    public Bed get(int id) {
        return bedMapper.get(id);
    }

    @Override
    public void add(Bed bed) {
        bedMapper.add(bed);
    }

    @Override
    public void edit(Bed bed) {
        bedMapper.edit(bed);
    }

    @Override
    public void del(int id) {
        bedMapper.del(id);
    }
}
