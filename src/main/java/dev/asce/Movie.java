package dev.asce;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Movie extends PanacheEntity{
    @Column(unique=true)
    public String title;
}
