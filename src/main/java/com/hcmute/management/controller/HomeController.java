package com.hcmute.management.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.hcmute.management.constant.FilePathConstant.*;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("activeFlag", "home");
        return HOME_PAGE_PATH;
    }

    @GetMapping("/")
    public String getIndex() {
        return HOME_PAGE_PATH;
    }



    @GetMapping("/login")
    public String getLoginPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "redirect:/";
        }
        return LOGIN_PAGE_PATH;
    }
}
