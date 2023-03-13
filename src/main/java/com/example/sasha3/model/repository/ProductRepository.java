package com.example.sasha3.model.repository;

import com.example.sasha3.model.BaseRepository;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.entity.ProductTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {
    List<ProductEntity> findAllByProductType(ProductTypeEntity productType);
}
