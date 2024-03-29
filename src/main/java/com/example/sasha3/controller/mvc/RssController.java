package com.example.sasha3.controller.mvc;

import com.example.sasha3.model.repository.NewsRepository;
import com.example.sasha3.model.repository.RssUrlRepository;
import com.example.sasha3.model.entity.RssUrl;
import com.example.sasha3.utils.RssUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RssController {

    private final RssUrlRepository rssUrlRepository;
    private final NewsRepository newsRepository;

    @GetMapping("/rss")
    public String getRssHTML(Model model) {

        var items = newsRepository.findAll();
        var news = new ArrayList<>(items);

        var urls = rssUrlRepository.findAll().stream().map(RssUrl::getUrl).toList();
        if (!urls.isEmpty()) {
            urls.forEach(url -> {
                var itemsFromRss = RssUtils.getNewsFromRss(url);
                news.addAll(itemsFromRss);
            });
        }
        model.addAttribute("news", news);
        return "rss";
    }

    @GetMapping("/rss/urls")
    public String getRssUrlsHTML(Model model, @CookieValue(value = "JSESSIONID", required = false) String token) {
        if (StringUtils.isBlank(token)) return "redirect:/auth";
        var urls = rssUrlRepository.findAll();
        model.addAttribute("urls", urls);
        return "rssUrls";
    }

    @GetMapping("/rss/new")
    public String getRssNewHTML(@CookieValue(value = "JSESSIONID", required = false) String token) {
        if (StringUtils.isBlank(token)) return "redirect:/auth";
        return "rssNew";
    }
}