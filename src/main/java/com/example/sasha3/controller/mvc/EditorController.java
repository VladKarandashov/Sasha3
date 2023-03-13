package com.example.sasha3.controller.mvc;

import com.example.sasha3.model.repository.ProductRepository;
import com.example.sasha3.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EditorController {

    private final EditorService editorService;

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model) {
        editorService.fillModel(id, model);
        return "/editor";
    }

    @GetMapping
    public String show() {
        return "redirect:/editor/" + editorService.getFirstProduct();
    }
}
