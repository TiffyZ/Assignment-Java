package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Entry;
import com.example.springbootdemo.domain.EntryResponseDTO;

import java.util.Collection;
import java.util.List;

public interface EntryService {
    List<Entry> findAll();
    List<Entry> findAll(String auth);

}
