package com.example.soccerteammanager;

public class Player implements SoccerEntity {
    private final String name;
    private final int age;
    private final String country;
    private final String position;
    private final String team;
    private final int jerseyNumber;

    public Player(String name, int age, String country, String position, String team, int jerseyNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Invalid age");
        }
        this.name = name;
        this.age = age;
        this.country = country;
        this.position = position;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + age + ", " + country + ") - " + position + " | " + team + " #" + jerseyNumber;
    }
}