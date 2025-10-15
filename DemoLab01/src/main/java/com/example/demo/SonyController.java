package com.example.demo;

import com.example.demo.model.entity.SonyAccount;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        if(sonyService.login(acc.getPhone(), acc.getPassword()) != null){
            ss.setAttribute("acc", acc);
            return "management";
        }else{
            redirectAttributes.addFlashAttribute("errorMsg", "You do not have permission to access this function!");

            return "redirect:/sony/login";
        }

    }

}
