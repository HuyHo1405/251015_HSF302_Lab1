package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sony_products")
@Getter
@Setter
@NoArgsConstructor
public class SonyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name= "product_name", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String productName;

    @Column(name= "price", nullable = false)
    private int price;

    @Column(name= "stock", nullable = false)
    private int stock;

    @Column(name= "create_at", columnDefinition = "DATETIME", nullable = false)
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "cate_id", nullable = false)
    private SonyCategory category;

    public SonyProduct(String productName, int price, int stock, LocalDate createAt, SonyCategory category) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.createAt = createAt;
        this.category = category;
    }
}
