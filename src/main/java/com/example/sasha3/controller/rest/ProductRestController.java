package com.example.sasha3.controller.rest;

import com.example.sasha3.model.dto.MiniProductDto;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.service.ProductService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/delete")
    public void delete(@RequestBody DeleteRequest request) {
        Long id = request.getId();
        productService.deleteProduct(id);
    }

    @PostMapping("/save")
    public ProductEntity save(@Valid @RequestBody MiniProductDto request) {
        return productService.saveProduct(request);
    }

    @Data
    private static class DeleteRequest {
        Long id;
    }
}
