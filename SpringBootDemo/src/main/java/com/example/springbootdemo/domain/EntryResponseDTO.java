package com.example.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryResponseDTO {
    private int count;
    private List<Entry> entries;
}
