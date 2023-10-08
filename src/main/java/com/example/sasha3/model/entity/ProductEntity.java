package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity {
    private Double weight;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
    private Integer warrantyPeriod;

    @ManyToOne(cascade = {CascadeType.ALL})
    private ProductTypeEntity productType;
    @ManyToOne(cascade = {CascadeType.ALL})
    private ProductLineEntity productLine;
    @ManyToOne(cascade = {CascadeType.ALL})
    private WarrantyTypeEntity warrantyType;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliveryTypeEntity deliveryType;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderLink> orders;
}
