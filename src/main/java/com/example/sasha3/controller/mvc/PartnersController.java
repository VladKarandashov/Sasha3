package com.example.sasha3.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PartnersController {

    @GetMapping("/partners")
    public String getPartnersHTML() {
        return "/partners";
    }
}