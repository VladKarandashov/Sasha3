package com.example.sasha3.service;

import com.example.sasha3.model.entity.DeliveryTypeEntity;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.entity.ProductTypeEntity;
import com.example.sasha3.model.entity.WarrantyTypeEntity;
import com.example.sasha3.model.repository.DeliveryTypeRepository;
import com.example.sasha3.model.repository.ProductRepository;
import com.example.sasha3.model.repository.ProductTypeRepository;
import com.example.sasha3.model.repository.WarrantyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SearchService {
    private final ProductTypeRepository productTypeRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final WarrantyTypeRepository warrantyTypeRepository;

    private final ProductRepository productRepository;

    public void fillModel(Long idProductType, Long idDeliveryType, Long idWarrantyType, Model model) {
        model.addAttribute("idProductType", idProductType);
        model.addAttribute("idDeliveryType", idDeliveryType);
        model.addAttribute("idWarrantyType", idWarrantyType);

        var productType = idProductType == 0 ?
                new ProductTypeEntity() :
                productTypeRepository.findById(idProductType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var deliveryType = idDeliveryType == 0 ?
                new DeliveryTypeEntity() :
                deliveryTypeRepository.findById(idDeliveryType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var warrantyType = idWarrantyType == 0 ?
                new WarrantyTypeEntity() :
                warrantyTypeRepository.findById(idWarrantyType).orElseThrow(() -> new RuntimeException("Не твори фигню!"));

        var products = productRepository.findAllBy().parallelStream()
                .filter(el -> idProductType == 0 || productType.equals(el.getProductType()))
                .filter(el -> idDeliveryType == 0 || deliveryType.equals(el.getDeliveryType()))
                .filter(el -> idWarrantyType == 0 || warrantyType.equals(el.getWarrantyType()))
                .toList();
        model.addAttribute("products", products);

        var productTypes = products.stream()
                .map(ProductEntity::getProductType)
                .distinct()
                .sorted(Comparator.comparing(ProductTypeEntity::getTitle))
                .toList();
        var deliveryTypes = products.stream()
                .map(ProductEntity::getDeliveryType)
                .distinct()
                .sorted(Comparator.comparing(DeliveryTypeEntity::getTitle))
                .toList();
        var warrantyTypes = products.stream()
                .map(ProductEntity::getWarrantyType)
                .distinct()
                .sorted(Comparator.comparing(WarrantyTypeEntity::getTitle))
                .toList();
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("deliveryTypes", deliveryTypes);
        model.addAttribute("warrantyTypes", warrantyTypes);
    }
}
