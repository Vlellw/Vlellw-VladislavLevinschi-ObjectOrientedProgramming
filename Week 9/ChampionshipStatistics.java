package main;

import java.util.*;

public class ChampionshipStatistics {
    public static double calculateAveragePointsPerDriver() {
        int total = ChampionshipManager.calculateTotalChampionshipPoints();
        int count = ChampionshipManager.getTotalDrivers();
        return count == 0 ? 0.0 : (double) total / count;
    }

    public static String findMostSuccessfulCountry() {
        Map<String, Integer> map = new HashMap<>();
        for (Driver d : ChampionshipManager.getDrivers()) {
            map.merge(d.getCountry(), d.getTotalPoints(), Integer::sum);
        }
        return map.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("None");
    }

    public static int countTotalRacesHeld() {
        return ChampionshipManager.getTotalRaces();
    }
}
