package com.example.demo;


import com.example.demo.model.entity.SonyAccount;

public interface SonyService {
    SonyAccount login(String phone, String pw);
    void logout(String token);
}
