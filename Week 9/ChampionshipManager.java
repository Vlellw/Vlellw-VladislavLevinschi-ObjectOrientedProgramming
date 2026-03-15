package main;

import java.util.*;

public class ChampionshipManager {
    private static ChampionshipManager instance;
    private final List<Driver> drivers = new ArrayList<>();
    private final List<RaceResult> races = new ArrayList<>();
    private static int totalDriversRegistered = 0;
    private static int totalRacesHeld = 0;

    private ChampionshipManager() {}

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public static void registerDriver(Driver driver) {
        getInstance().drivers.add(driver);
        totalDriversRegistered++;
    }

    public static void addRaceResult(RaceResult raceResult) {
        getInstance().races.add(raceResult);
        totalRacesHeld++;
    }

    public static List<Driver> getChampionshipStandings() {
        List<Driver> sorted = new ArrayList<>(getInstance().drivers);
        sorted.sort((d1, d2) -> Integer.compare(d2.getTotalPoints(), d1.getTotalPoints()));
        return sorted;
    }

    public static Driver getLeadingDriver() {
        List<Driver> s = getChampionshipStandings();
        return s.isEmpty() ? null : s.get(0);
    }

    public static int calculateTotalChampionshipPoints() {
        return getInstance().drivers.stream().mapToInt(Driver::getTotalPoints).sum();
    }

    public static List<RaceResult> getRaceResults() {
        return new ArrayList<>(getInstance().races);
    }

    public static List<Driver> getDrivers() {
        return new ArrayList<>(getInstance().drivers);
    }

    public static int getTotalDrivers() { return totalDriversRegistered; }
    public static int getTotalRaces() { return totalRacesHeld; }
}
