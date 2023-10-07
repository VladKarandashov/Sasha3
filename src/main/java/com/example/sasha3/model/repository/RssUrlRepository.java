package com.example.sasha3.model.repository;

import com.example.sasha3.model.entity.RssUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RssUrlRepository extends JpaRepository<RssUrl, Long> {
    boolean existsById(Long id);
}