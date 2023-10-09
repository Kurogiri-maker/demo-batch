package com.cherif.iheb;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
