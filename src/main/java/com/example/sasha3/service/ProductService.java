package com.example.sasha3.service;

import com.example.sasha3.model.dto.MiniProductDto;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderLinkService orderLinkService;
    private final ProductTypeRepository productTypeRepository;
    private final ProductLineRepository productLineRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final WarrantyTypeRepository warrantyTypeRepository;

    @Transactional
    public void deleteProduct(Long id) {
        if (id == null || !productRepository.existsById(id)) {
            throw new RuntimeException();
        }
        var product = productRepository.findById(id).orElseThrow();
        product.setProductType(null);
        product.setProductLine(null);
        product.setWarrantyType(null);
        product.setDeliveryType(null);
        product.setOrders(new ArrayList<>());
        product = productRepository.save(product);
        orderLinkService.deleteOrderLinkByProduct(product);
        productRepository.delete(product);
        log.debug("Удаление успешно");
    }

    public ProductEntity saveProduct(MiniProductDto request) {
        log.debug("Запрос на сохранение");
        log.debug(request.toString());

        ProductEntity product = ProductEntity.builder()
                .weight(request.getWeight())
                .date(request.getDate())
                .warrantyPeriod(request.getWarrantyPeriod())

                .productType(request.getProductType() == 0 ? null : productTypeRepository.findById(request.getProductType()).orElseThrow())
                .productLine(request.getProductLine() == 0 ? null : productLineRepository.findById(request.getProductLine()).orElseThrow())
                .deliveryType(request.getDeliveryType() == 0 ? null : deliveryTypeRepository.findById(request.getDeliveryType()).orElseThrow())
                .warrantyType(request.getWarrantyType() == 0 ? null : warrantyTypeRepository.findById(request.getWarrantyType()).orElseThrow())
                .build();
        product.setId(request.getId() == null ? null : request.getId());
        product.setTitle(request.getTitle());
        return productRepository.save(product);
    }
}
