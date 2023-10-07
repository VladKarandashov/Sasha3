package com.example.sasha3.controller.rest;

import com.example.sasha3.model.dto.NewsRequest;
import com.example.sasha3.model.dto.RssLinkRequest;
import com.example.sasha3.model.entity.NewsItem;
import com.example.sasha3.model.entity.RssUrl;
import com.example.sasha3.model.repository.NewsRepository;
import com.example.sasha3.model.repository.RssUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rss")
public class RssRestController {

    private final RssUrlRepository rssUrlRepository;
    private final NewsRepository newsRepository;

    @PostMapping("/urls")
    public ResponseEntity<Void> createNewRssLink(@RequestBody RssLinkRequest request) {
        var url = request.getUrl();
        rssUrlRepository.save(new RssUrl(url));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/urls/{id}")
    public ResponseEntity<Void> deleteRssLink(@PathVariable("id") Long id) {
        if (rssUrlRepository.existsById(id)) rssUrlRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createNewNews(@RequestBody NewsRequest request) {
        var newsItem = NewsItem.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .link(request.getLink())
                .author(request.getAuthor())
                .pubDate(LocalDate.now().toString())
                .imageLink(request.getImageLink())
                .build();
        newsRepository.save(newsItem);
        return ResponseEntity.ok().build();
    }
}