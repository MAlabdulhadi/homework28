package com.example.hw28.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;
    @Column(columnDefinition = "decimal not null")
    private Double price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Order> orders;

}
