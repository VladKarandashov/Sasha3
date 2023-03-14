package com.example.sasha3.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
@ToString
public class MiniProductDto {

    private Long id;
    @NotBlank
    private String title;
    @Min(0)
    private Double weight;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
    @Min(0)
    private Integer warrantyPeriod;

    private Long productType;
    private Long productLine;
    private Long deliveryType;
    private Long warrantyType;

}
