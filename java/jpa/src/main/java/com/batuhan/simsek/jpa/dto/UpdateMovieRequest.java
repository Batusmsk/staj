package com.batuhan.simsek.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieRequest {
	private Integer id;
	private String title;
	private String imdb;
	private Integer year;
}
