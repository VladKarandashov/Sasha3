package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProductLineEntity extends BaseEntity {

    public ProductLineEntity(String title) {
        super(title);
    }
}
