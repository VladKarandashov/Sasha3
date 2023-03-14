package com.example.sasha3.model.entity;

import com.example.sasha3.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
public class OrderEntity extends BaseEntity {
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderLink> products;

    public OrderEntity(String title) {
        super(title);
    }
}
