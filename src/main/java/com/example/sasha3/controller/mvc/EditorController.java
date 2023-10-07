package com.example.sasha3.controller.mvc;

import com.example.sasha3.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EditorController {

    private final EditorService editorService;

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model, @CookieValue(value = "JSESSIONID", required = false) String token) {
        if (StringUtils.isBlank(token)) return "redirect:/auth";
        editorService.fillModel(id, model);
        return "/editor";
    }

    @GetMapping
    public String show(@CookieValue(value = "JSESSIONID", required = false) String token) {
        if (StringUtils.isBlank(token)) return "redirect:/auth";
        return "redirect:/editor/" + editorService.getFirstProduct();
    }
}
