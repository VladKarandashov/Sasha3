package com.example.sasha3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountOrdersByProductType {
    private String productTypeTitle;
    private Long count;
}
