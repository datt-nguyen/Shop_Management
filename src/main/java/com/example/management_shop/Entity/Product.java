package com.example.management_shop.Entity;

import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private Double  price;
    private Integer stock;
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
