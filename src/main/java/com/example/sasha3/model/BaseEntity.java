package com.example.sasha3.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private T title;

    public BaseEntity(T title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
