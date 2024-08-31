package com.example.demo.services;

import com.example.demo.domain.po.Bed;

import java.util.List;

public interface BedServices {
    List<Bed> getAll();
    Bed get(int id);
    void add(Bed bed);
    void edit(Bed bed);
    void del(int id);
}
