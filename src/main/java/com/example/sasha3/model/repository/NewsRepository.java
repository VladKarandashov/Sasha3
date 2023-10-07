package com.example.sasha3.model.repository;

import com.example.sasha3.model.entity.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsItem, Long> {
}