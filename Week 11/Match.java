package com.example.soccerteammanager;

public class Match implements SoccerEntity {
    private final String homeTeam;
    private final String awayTeam;
    private final String score;
    private final String competition;
    private final String date;
    private final String venue;

    public Match(String homeTeam, String awayTeam, String score, String competition, String date, String venue) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Teams cannot be null");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.competition = competition;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public String getName() { return homeTeam + " vs " + awayTeam; }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam + " (" + score + ")\n" + competition + " | " + date + " at " + venue;
    }
}