package com.batuhan.simsek.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;





@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movieid")
    @Getter
    @Setter
    private Integer movieID;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private int year;
    @Getter
    @Setter
    private String imdb;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER, mappedBy = "movie")
    @OrderBy("id ASC")
    private List<Actor> actor;
    
    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", imdb='" + imdb + '\'' +
                '}';
    }
}
