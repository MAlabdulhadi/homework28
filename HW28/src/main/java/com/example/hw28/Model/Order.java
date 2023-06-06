package com.example.hw28.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer quantity;
    @Column(columnDefinition = "decimal not null")
    private Double totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private Date date;
    @Column(columnDefinition = "varchar(30) not null check (status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(columnDefinition = "customerId", referencedColumnName = "id")
    private User customer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(columnDefinition = "productId", referencedColumnName = "id")
    private Product product;


}
