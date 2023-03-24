package com.example.springbootdemo.domain.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    private String API;
    private String Description;
    private String Auth;
    @Getter
    private boolean HTTPS;

    private String Cors;
    private String Link;
    private String Category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry e = (Entry) o;
        return Link == e.Link;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Link);
    }
}