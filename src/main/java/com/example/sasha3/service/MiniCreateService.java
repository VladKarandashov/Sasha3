package com.example.sasha3.service;

import com.example.sasha3.model.dto.CreateResponse;
import com.example.sasha3.model.dto.Item;
import com.example.sasha3.model.entity.*;
import com.example.sasha3.model.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MiniCreateService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductLineRepository productLineRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final WarrantyTypeRepository warrantyTypeRepository;
    private final OrderRepository orderRepository;

    public CreateResponse miniCreate(@NonNull String title, String obj) {
        switch (obj) {
            case "productType" -> {
                return createProductType(title);
            }
            case "productLine" -> {
                return createProductLine(title);
            }
            case "deliveryType" -> {
                return createDeliveryType(title);
            }
            case "warrantyType" -> {
                return createWarrantyType(title);
            }
            case "order" -> {
                return createOrder(title);
            }
        }
        return null;
    }

    private CreateResponse createProductType(String title) {
        assert productTypeRepository.existsByTitle(title);
        var newObj = productTypeRepository.save(new ProductTypeEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = productTypeRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createProductLine(String title) {
        assert productLineRepository.existsByTitle(title);
        var newObj = productLineRepository.save(new ProductLineEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = productLineRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createDeliveryType(String title) {
        assert deliveryTypeRepository.existsByTitle(title);
        var newObj = deliveryTypeRepository.save(new DeliveryTypeEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = deliveryTypeRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createWarrantyType(String title) {
        assert warrantyTypeRepository.existsByTitle(title);
        var newObj = warrantyTypeRepository.save(new WarrantyTypeEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = warrantyTypeRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createOrder(String title) {
        assert orderRepository.existsByTitle(title);
        var newObj = orderRepository.save(new OrderEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = orderRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }
}
