package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sony_categories")
@Getter
@Setter
@NoArgsConstructor
public class SonyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name= "cate_name", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String cateName;

    @Column(name= "status", columnDefinition = "NVARCHAR(10)", nullable = false)
    private String status;

    public SonyCategory(String cateName, String status) {
        this.cateName = cateName;
        this.status = status;
    }
}
