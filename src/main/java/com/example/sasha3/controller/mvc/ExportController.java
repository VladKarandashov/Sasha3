package com.example.sasha3.controller.mvc;

import com.example.sasha3.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/export")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExportController {

    private final ExportService exportService;

    @GetMapping
    public String show(Model model) {
        exportService.fillModel(model);
        return "/export";
    }

    @GetMapping("/XML")
    public String getHelpHTML(Model model) {
        exportService.fillModel(model);
        return "/exportXML";
    }
}
