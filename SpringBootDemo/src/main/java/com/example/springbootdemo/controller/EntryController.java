package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entry")
public class EntryController {
    private final EntryService entryService;
    @Autowired
    public EntryController(EntryService entryService){
        this.entryService = entryService;
    }
    @GetMapping
    public ResponseEntity<?> getAllEntry(){
        return new ResponseEntity<>(entryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"authName"})
    public ResponseEntity<?> getAllEntryByAuth(@RequestParam String authName) {
        return new ResponseEntity<>(entryService.findAll(authName), HttpStatus.OK);
    }
}
