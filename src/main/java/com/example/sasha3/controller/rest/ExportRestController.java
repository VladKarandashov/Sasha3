package com.example.sasha3.controller.rest;

import com.example.sasha3.service.ExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExportRestController {

    private final ExportService exportService;

    @GetMapping("/file")
    public ResponseEntity<Resource> exportFile() throws JsonProcessingException {
        return exportService.jsonFile();
    }
}
