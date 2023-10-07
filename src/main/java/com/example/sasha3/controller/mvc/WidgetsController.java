package com.example.sasha3.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WidgetsController {

    @GetMapping("/services")
    public String getWidgets() {
        return "services";
    }
}