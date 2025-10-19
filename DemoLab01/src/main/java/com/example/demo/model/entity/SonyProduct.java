package com.example.demo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Size(min = 5, max = 50, message = "Product name must be between 5 and 50 characters")
    private String productName;

    @Column(name= "price", nullable = false)
    @Min(value = 100, message = "Price must be at least 100")
    private int price;

    @Column(name= "stock", nullable = false)
    @Min(value = 0, message = "Stock must be at least 0")
    @Max(value = 1000, message = "Stock must not exceed 1000")
    private int stock;

    @Column(name= "create_at", columnDefinition = "DATETIME", nullable = false)
    private LocalDate createAt;

    @NotNull(message = "Category is required")
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
