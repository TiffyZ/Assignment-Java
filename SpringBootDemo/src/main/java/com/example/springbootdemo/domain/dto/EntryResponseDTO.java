package com.example.springbootdemo.domain.dto;

import com.example.springbootdemo.domain.entry.Entry;

public class EntryResponseDTO {
    private String API;
    private String Description;
    private String Auth;
    private boolean HTTPS;
    private String Cors;
    private String Link;
    private String Category;

    public EntryResponseDTO(Entry e){
        API = e.getAPI();
        Description = e.getDescription();
        Auth = e.getAuth();
        HTTPS = e.isHTTPS();
        Cors = e.getCors();
        Link = e.getLink();
        Category = e.getCategory();
    }
}
