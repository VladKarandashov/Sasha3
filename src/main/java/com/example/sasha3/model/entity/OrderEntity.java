package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class OrderEntity extends BaseEntity<String> {
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;

    @OneToMany(cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<OrderLink> products;
}
