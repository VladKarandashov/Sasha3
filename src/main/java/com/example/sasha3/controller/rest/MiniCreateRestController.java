package com.example.sasha3.controller.rest;

import com.example.sasha3.model.dto.CreateResponse;
import com.example.sasha3.service.MiniCreateService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniCreate")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MiniCreateRestController {

    private final MiniCreateService miniCreateService;

    @PostMapping("/productType")
    public CreateResponse productType(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "productType");
    }

    @PostMapping("/productLine")
    public CreateResponse productLine(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "productLine");
    }

    @PostMapping("/deliveryType")
    public CreateResponse deliveryType(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "deliveryType");
    }

    @PostMapping("/warrantyType")
    public CreateResponse warrantyType(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "warrantyType");
    }

    @PostMapping("/order")
    public CreateResponse order(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "order");
    }

    @Data
    @ToString
    @NoArgsConstructor
    private static class CreateRequest {
        private String title;
    }
}
