package com.batuhan.simsek.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;





@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actorId")
    @Getter
    @Setter
    private Integer actorId;
    
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String lastName;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;
  
}
