package com.example.springbootdemo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @JsonProperty("API")
    private String API;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Auth")
    private String Auth;
    @JsonProperty("HTTPS")
    private boolean HTTPS;
    @JsonProperty("Cors")
    private String Cors;
    @JsonProperty("Link")
    private String Link;
    @JsonProperty("Category")
    private String Category;

}