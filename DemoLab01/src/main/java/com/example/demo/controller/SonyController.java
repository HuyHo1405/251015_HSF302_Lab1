package com.example.demo.controller;

import com.example.demo.model.entity.SonyProduct;
import com.example.demo.service.SonyService;
import com.example.demo.model.entity.SonyAccount;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SonyController {

    private final SonyService sonyService;

    @GetMapping({"/", "/login"})
    public String toLogin(Model model) {
        model.addAttribute("acc", new SonyAccount());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("acc") SonyAccount acc, HttpSession ss, RedirectAttributes redirectAttributes) {
        SonyAccount a = sonyService.login(acc.getPhone(), acc.getPassword());
        if(a != null){
            ss.setAttribute("acc", a);
            return "redirect:/management";
        }else{
            redirectAttributes.addFlashAttribute("errorMsg", "You do not have permission to access this function!");
            return "redirect:/login";
        }
    }

    @GetMapping("/management")
    public String toManagement(Model model, HttpSession ss) {
        if(checkAuth(ss)) return "redirect:/login";
        model.addAttribute("productList", sonyService.getAllProducts());
        return "management";
    }

    @GetMapping("/product/{productId}/delete")
    public String deleteProduct(
            Model model,
            HttpSession ss,
            @PathVariable Long productId
    ) {
        if(checkAuth(ss)) return "redirect:/login";
        if(checkAdmin(ss)) return "redirect:/management";
        sonyService.deleteProduct(productId);
        return  "redirect:/management";
    }

    @GetMapping("/product/{productId}")
    public String toEditProductForm(
            HttpSession ss,
            Model model,
            @PathVariable Long productId
    ){
        if(checkAuth(ss)) return "redirect:/login";
        if(checkAdmin(ss)) return "redirect:/management";
        SonyProduct p = sonyService.getByProductId(productId);
        if(p == null) return "redirect:/management";
        model.addAttribute("product",p);
        model.addAttribute("categoryList", sonyService.getAllCategories());
        return "product-form";
    }

    @GetMapping("/product/add")
    public String toAddProductForm(
            HttpSession ss,
            Model model
    ){
        if(checkAuth(ss)) return "redirect:/login";
        if(checkAdmin(ss)) return "redirect:/management";
        model.addAttribute("product", new SonyProduct());
        model.addAttribute("categoryList", sonyService.getAllCategories());
        return "product-form";
    }

    @PostMapping({"/product/add", "/product/{productId}/update"})
    public String saveProduct(
            @Valid @ModelAttribute("product") SonyProduct product,
            BindingResult result, // ← PHẢI THÊM CÁI NÀY (đặt ngay sau @Valid)
            HttpSession ss,
            Model model, // ← Thêm Model để trả lại categoryList khi có lỗi
            RedirectAttributes ra
    ) {
        if(checkAuth(ss)) return "redirect:/login";
        if(checkAdmin(ss)) return "redirect:/management";

        if(result.hasErrors()) {
            // Trả lại categoryList để form vẫn hiển thị đúng
            model.addAttribute("categoryList", sonyService.getAllCategories());
            return "product-form"; // Quay lại form, giữ nguyên data + hiển thị lỗi
        }

        sonyService.save(product);
        ra.addFlashAttribute("successMsg", "Product saved successfully!");
        return "redirect:/management";
    }

    private boolean checkAuth(HttpSession ss) {
        return ss.getAttribute("acc") == null;
    }

    private boolean checkAdmin(HttpSession ss) {
        SonyAccount acc = (SonyAccount) ss.getAttribute("acc");
        return acc.getRoleId() != 1;
    }

}
