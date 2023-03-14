package com.example.sasha3.service;

import com.example.sasha3.model.repository.OrderLinkRepository;
import com.example.sasha3.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderLinkService orderLinkService;

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

}
