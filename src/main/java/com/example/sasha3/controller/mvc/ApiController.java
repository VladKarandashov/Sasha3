package com.example.sasha3.controller.mvc;

import com.example.sasha3.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final SearchService searchService;

    @GetMapping
    public String show(Model model){
        model.addAttribute("time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
        searchService.fillModel(0L, 0L, 0L, model);
        return "api";
    }
}
