package com.example.sasha3.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/help")
public class HelpController {
    @GetMapping
    public String show(Model model){
        model.addAttribute("time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
        return "/help";
    }
}
