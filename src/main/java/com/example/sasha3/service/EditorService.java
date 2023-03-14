package com.example.sasha3.service;

import com.example.sasha3.model.entity.*;
import com.example.sasha3.model.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EditorService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductLineRepository productLineRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final WarrantyTypeRepository warrantyTypeRepository;
    private final OrderRepository orderRepository;

    public Long getFirstProduct() {
        return productRepository.findFirstRowId();
    }

    public void fillModel(Long id, Model model) {
        Long firstFilmId = productRepository.findFirstRowId();
        Long lastFilmId = productRepository.findLastRowId();
        model.addAttribute("firstId", firstFilmId);
        model.addAttribute("lastId", lastFilmId);

        Long prevId = productRepository.getPreviousId(id);
        Long nextId = productRepository.getNextId(id);
        prevId = prevId == null ? lastFilmId : prevId;
        nextId = nextId == null ? firstFilmId : nextId;
        model.addAttribute("prevId", prevId);
        model.addAttribute("nextId", nextId);

        ProductEntity product;
        if (id == 0) {
            product = ProductEntity.builder()
                    .productType(new ProductTypeEntity())
                    .productLine(new ProductLineEntity())
                    .warrantyType(new WarrantyTypeEntity())
                    .deliveryType(new DeliveryTypeEntity())
                    .build();
            model.addAttribute("orders", new ArrayList<OrderLink>());
        } else {
            product = productRepository.findById(id).orElseThrow();
            if (product.getProductType() == null) product.setProductType(new ProductTypeEntity());
            if (product.getProductLine() == null) product.setProductLine(new ProductLineEntity());
            if (product.getWarrantyType() == null) product.setWarrantyType(new WarrantyTypeEntity());
            if (product.getDeliveryType() == null) product.setDeliveryType(new DeliveryTypeEntity());
            model.addAttribute("orders", product.getOrders());
        }

        model.addAttribute("product", product);

        model.addAttribute("productTypes", productTypeRepository.findAll().stream()
                .sorted(Comparator.comparing(ProductTypeEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("productLines", productLineRepository.findAll().stream()
                .sorted(Comparator.comparing(ProductLineEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("deliveryTypes", deliveryTypeRepository.findAll().stream()
                .sorted(Comparator.comparing(DeliveryTypeEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("warrantyTypes", warrantyTypeRepository.findAll().stream()
                .sorted(Comparator.comparing(WarrantyTypeEntity::getTitle))
                .collect(Collectors.toList()));
    }
}
