package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.dto.EntryResponseDTO;

import java.util.Collection;

public interface EntryService {
    Collection<EntryResponseDTO> findAll();
    Collection<EntryResponseDTO> filterByAuth();

}
