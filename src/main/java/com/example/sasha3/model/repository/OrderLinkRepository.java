package com.example.sasha3.model.repository;

import com.example.sasha3.model.BaseRepository;
import com.example.sasha3.model.entity.OrderLink;
import com.example.sasha3.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLinkRepository extends JpaRepository<OrderLink, Long> {
    void deleteAllByProduct(ProductEntity product);
    List<OrderLink> findAllByProduct(ProductEntity product);
}
