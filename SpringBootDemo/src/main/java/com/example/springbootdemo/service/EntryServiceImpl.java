package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.dto.EntryResponseDTO;
import com.example.springbootdemo.domain.entry.Entry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class EntryServiceImpl implements EntryService{
    public Collection<EntryResponseDTO> findAll() {
        String link = "https://api.publicapis.org/entries";
        try{
            URL url = new URL(link);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Entry> EntryList = objectMapper.readValue(url, new TypeReference<List<Entry>>() {});
            for (Entry Entry : EntryList) {
                System.out.println(Entry);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Collection<EntryResponseDTO> filterByAuth() {
        return null;
    }

    public static void main(String[] args) {
        new EntryServiceImpl().findAll();
    }
}
