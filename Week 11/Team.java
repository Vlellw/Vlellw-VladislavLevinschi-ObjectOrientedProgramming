package com.example.soccerteammanager;

public class Team implements SoccerEntity {
    private final String name;
    private final String country;
    private final String league;
    private final String stadium;
    private final int foundedYear;

    public Team(String name, String country, String league, String stadium, int foundedYear) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be empty");
        }
        this.name = name;
        this.country = country;
        this.league = league;
        this.stadium = stadium;
        this.foundedYear = foundedYear;
    }

    @Override
    public String getName() { return name; }
    public int getFoundedYear() { return foundedYear; }

    @Override
    public String toString() {
        return name + "\n" + country + " | " + league + "\nStadium: " + stadium + " | Founded: " + foundedYear;
    }
}