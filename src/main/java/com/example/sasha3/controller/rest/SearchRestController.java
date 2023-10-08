package com.example.sasha3.controller.rest;

import com.example.sasha3.model.entity.DeliveryTypeEntity;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.entity.ProductTypeEntity;
import com.example.sasha3.model.entity.WarrantyTypeEntity;
import com.example.sasha3.model.repository.DeliveryTypeRepository;
import com.example.sasha3.model.repository.ProductRepository;
import com.example.sasha3.model.repository.ProductTypeRepository;
import com.example.sasha3.model.repository.WarrantyTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchRestController {

    private final ProductTypeRepository productTypeRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final WarrantyTypeRepository warrantyTypeRepository;

    private final ProductRepository productRepository;

    @GetMapping("/api/search")
    public List<ProductEntity> show(@RequestParam(required = false, defaultValue = "0") Long idProductType,
                                    @RequestParam(required = false, defaultValue = "0") Long idDeliveryType,
                                    @RequestParam(required = false, defaultValue = "0") Long idWarrantyType) {
        var productType = idProductType == 0 ?
                new ProductTypeEntity() :
                productTypeRepository.findById(idProductType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var deliveryType = idDeliveryType == 0 ?
                new DeliveryTypeEntity() :
                deliveryTypeRepository.findById(idDeliveryType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var warrantyType = idWarrantyType == 0 ?
                new WarrantyTypeEntity() :
                warrantyTypeRepository.findById(idWarrantyType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));

        return productRepository.findAllBy().parallelStream()
                .filter(el -> idProductType == 0 || productType.equals(el.getProductType()))
                .filter(el -> idDeliveryType == 0 || deliveryType.equals(el.getDeliveryType()))
                .filter(el -> idWarrantyType == 0 || warrantyType.equals(el.getWarrantyType()))
                .toList();
    }
}
