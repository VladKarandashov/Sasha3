package com.example.sasha3.service;

import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.repository.OrderLinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class OrderLinkService {

    private final OrderLinkRepository orderLinkRepository;

    public void deleteOrderLinkByProduct(ProductEntity product){
        orderLinkRepository.findAllByProduct(product).forEach(link -> {
            link.setProduct(null);
            link.setOrder(null);
            orderLinkRepository.save(link);
            orderLinkRepository.deleteById(link.getId());
            log.debug("удалена связь");
        });
    }

}
