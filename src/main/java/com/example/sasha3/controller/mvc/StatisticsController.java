package com.example.sasha3.controller.mvc;

import com.example.sasha3.service.StatisticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping
    public String show(Model model) throws JsonProcessingException {

        model.addAttribute("countProductLines", statisticService.countProductLine());
        model.addAttribute("countProductTypes", statisticService.countProductType());
        model.addAttribute("countProducts", statisticService.countProduct());

        model.addAttribute("statistics", statisticService.jsonStatistics());

        return "/statistics";
    }
}
