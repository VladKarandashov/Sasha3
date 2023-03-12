package com.example.sasha3.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer count;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
