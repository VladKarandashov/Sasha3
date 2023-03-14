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
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public BaseEntity(String title) {
        this.title = title;
    }

    public BaseEntity(Object obj) {
        if (obj instanceof BaseEntity baseEntity) {
            this.id = baseEntity.getId();
            this.title = baseEntity.getTitle();
        } else {
            throw new RuntimeException("Это не BaseEntity!");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
