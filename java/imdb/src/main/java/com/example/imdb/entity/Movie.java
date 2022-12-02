package com.example.imdb.entity;

import java.util.Objects;

public class Movie {
    private Integer movieID;
    private String title;
    private int year;
    private String imdb;

    public Movie() {

    }

    public Movie(String title, int year, String imdb) {
        this.title = title;
        this.year = year;
        this.imdb = imdb;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && Objects.equals(movieID, movie.movieID) && Objects.equals(title, movie.title) && Objects.equals(imdb, movie.imdb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID, title, year, imdb);
    }

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
