package com.example.hw28.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class OrderDTO {

    private Integer productId;
    private Integer quantity;

}
