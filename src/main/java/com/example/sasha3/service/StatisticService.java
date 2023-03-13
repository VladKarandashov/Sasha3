package com.example.sasha3.service;

import com.example.sasha3.model.dto.CountOrdersByProductType;
import com.example.sasha3.model.entity.OrderLink;
import com.example.sasha3.model.entity.ProductTypeEntity;
import com.example.sasha3.model.repository.OrderRepository;
import com.example.sasha3.model.repository.ProductLineRepository;
import com.example.sasha3.model.repository.ProductRepository;
import com.example.sasha3.model.repository.ProductTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticService {
    private final ProductLineRepository productLineRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Long countProductLine() {
        return productLineRepository.count();
    }

    public Long countProductType() {
        return productTypeRepository.count();
    }

    public Long countProduct() {
        return productRepository.count();
    }

    public Long countOrder() {
        return orderRepository.count();
    }

    public List<CountOrdersByProductType> statistics() {
        return productTypeRepository.findAll().parallelStream()
                .map(productType ->
                        new CountOrdersByProductType(
                                productType.getTitle(),
                                getCountOrdersByProductType(productType)
                        )
                ).collect(Collectors.toList());
    }

    public String jsonStatistics() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(statistics());
    }


    public Long getCountOrdersByProductType(ProductTypeEntity productType) {
        return productRepository.findAllByProductType(productType).parallelStream()
                .mapToLong(product ->
                        product.getOrders().stream().mapToLong(OrderLink::getCount).sum()
                ).sum();
    }
}
