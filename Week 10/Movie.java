package com.example.myapplication;

public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    // Check with null and error checking
    public Movie(String title, Integer year, String genre, String posterResource) {
        this.title = (title != null && !title.trim().isEmpty()) ? title.trim() : null;
        this.year = (year != null && year > 0) ? year : null;           // if negative or zero == null
        this.genre = (genre != null && !genre.trim().isEmpty()) ? genre.trim() : null;
        this.posterResource = (posterResource != null && !posterResource.trim().isEmpty())
                ? posterResource.trim() : null;
    }

    // Getters
    public String getTitle() { return title; }
    public Integer getYear() { return year; }
    public String getGenre() { return genre; }
    public String getPosterResource() { return posterResource; }

    // Setters with the same checking
    public void setTitle(String title) {
        this.title = (title != null && !title.trim().isEmpty()) ? title.trim() : null;
    }
    public void setYear(Integer year) {
        this.year = (year != null && year > 0) ? year : null;
    }
    public void setGenre(String genre) {
        this.genre = (genre != null && !genre.trim().isEmpty()) ? genre.trim() : null;
    }
    public void setPosterResource(String posterResource) {
        this.posterResource = (posterResource != null && !posterResource.trim().isEmpty())
                ? posterResource.trim() : null;
    }
}
