package com.example.sasha3.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
    boolean existsByTitle(String title);

    Optional<E> findByTitle(String title);

    Stream<E> streamAllBy();

    List<E> findAllBy();
}
