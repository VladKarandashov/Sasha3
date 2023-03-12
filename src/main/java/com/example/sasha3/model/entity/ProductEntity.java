package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ProductEntity extends BaseEntity<String> {
    private Double weight;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;

    @ManyToOne(cascade = {CascadeType.ALL})
    private ProductTypeEntity productType;
    @ManyToOne(cascade = {CascadeType.ALL})
    private WarrantyPeriodEntity warrantyPeriod;
    @ManyToOne(cascade = {CascadeType.ALL})
    private ProductLineEntity productLine;
    @ManyToOne(cascade = {CascadeType.ALL})
    private WarrantyTypeEntity warrantyType;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliveryTypeEntity deliveryType;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<OrderLink> orders;
}
