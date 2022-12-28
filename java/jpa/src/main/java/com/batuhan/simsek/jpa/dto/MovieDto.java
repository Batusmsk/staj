package com.batuhan.simsek.jpa.dto;

import java.util.List;

import com.batuhan.simsek.jpa.entity.Actor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

	private Integer id;
    private String title;
    private int year;
    private String imdb;
    
    private List<ActorDto> actors;
}