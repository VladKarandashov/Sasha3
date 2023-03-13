package com.example.sasha3.service;

import com.example.sasha3.model.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExportService {

    private final ProductRepository productRepository;

    public void fillModel(Model model) {
        model.addAttribute("products", productRepository.findAllBy());
    }

    public ResponseEntity<Resource> jsonFile() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String films = objectMapper.writeValueAsString(productRepository.findAllBy().stream().peek(el -> el.setOrders(null)).toList());
        ByteArrayResource resource = new ByteArrayResource(films.getBytes());

        var currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        String currentDateTimeString = currentDateTime.format(formatter);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "products " + currentDateTimeString + ".json")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

}
