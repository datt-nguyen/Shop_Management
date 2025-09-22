package com.example.management_shop.Entity;

import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users   user;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
}
