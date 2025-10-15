package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sony_accounts")
@Getter
@Setter
@NoArgsConstructor

public class SonyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "phone", columnDefinition = "NVARCHAR(13)", nullable = false)
    private String phone;

    @Column(name= "password", columnDefinition = "NVARCHAR(13)", nullable = false)
    private String password;

    @Column(name= "role_id", nullable = false)
    private int roleId;

    public SonyAccount(String phone, String password, int roleId) {
        this.phone = phone;
        this.password = password;
        this.roleId = roleId;
    }
}
