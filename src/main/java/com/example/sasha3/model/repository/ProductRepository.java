package com.example.sasha3.model.repository;

import com.example.sasha3.model.BaseRepository;
import com.example.sasha3.model.entity.ProductEntity;
import com.example.sasha3.model.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {
    List<ProductEntity> findAllByProductType(ProductTypeEntity productType);

    @Query("SELECT id FROM ProductEntity ORDER BY id ASC LIMIT 1")
    Long findFirstRowId();
    @Query("SELECT id FROM ProductEntity ORDER BY id DESC LIMIT 1")
    Long findLastRowId();
    @Query("SELECT MAX(id) FROM ProductEntity WHERE id < :myId")
    Long getPreviousId(@Param("myId") Long myId);
    @Query("SELECT MIN(id) FROM ProductEntity WHERE id > :myId")
    Long getNextId(@Param("myId") Long myId);
}
