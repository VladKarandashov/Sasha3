package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProductTypeEntity extends BaseEntity {

    public ProductTypeEntity(String title) {
        super(title);
    }
}
