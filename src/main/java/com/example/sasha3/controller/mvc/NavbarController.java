package com.example.sasha3.controller.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class NavbarController {

    @GetMapping("/navbar")
    public String getNavBar() {
        return "/components/navigationBar";
    }

    @GetMapping("/footer")
    public String getFooter() {
        return "/components/floor";
    }
}