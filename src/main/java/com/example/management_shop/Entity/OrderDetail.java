package com.example.management_shop.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)

    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)

    private Product product;
}
