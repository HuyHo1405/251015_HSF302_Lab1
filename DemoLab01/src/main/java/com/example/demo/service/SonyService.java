package com.example.demo.service;


import com.example.demo.model.entity.SonyAccount;
import com.example.demo.model.entity.SonyCategory;
import com.example.demo.model.entity.SonyProduct;

import java.util.List;

public interface SonyService {
    SonyAccount login(String phone, String pw);
    void logout(String token);

    List<SonyProduct> getAllProducts();
    List<SonyCategory> getAllCategories();
    SonyProduct getByProductId(long productId);
    void deleteProduct(Long id);
    SonyProduct save(SonyProduct product);
}
