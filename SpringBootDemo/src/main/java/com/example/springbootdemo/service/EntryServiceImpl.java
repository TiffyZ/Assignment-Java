package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.EntryResponseDTO;
import com.example.springbootdemo.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EntryServiceImpl implements EntryService{
    private final RestTemplate restTemplate;
    private String url = "https://api.publicapis.org/entries";
    @Autowired
    public EntryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Entry> findAll() {
        EntryResponseDTO data = restTemplate.getForObject(url, EntryResponseDTO.class);
        return data.getEntries();
    }

    @Override
    public List<Entry> findAll(String auth) {
        EntryResponseDTO data = restTemplate.getForObject(url, EntryResponseDTO.class);
        return data.getEntries()
                .stream()
                .filter(e -> e.getAuth().equals(auth))
                .collect(Collectors.toList());
    }
}
