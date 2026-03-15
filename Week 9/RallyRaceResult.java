package main;

import java.util.*;

public class RallyRaceResult implements RaceResult {
    private final String name;
    private final String location;
    private final List<ResultEntry> entries = new ArrayList<>();

    private static class ResultEntry {
        final Driver driver;
        final int position;
        final int points;

        ResultEntry(Driver driver, int position, int points) {
            this.driver = driver;
            this.position = position;
            this.points = points;
        }
    }

    public RallyRaceResult(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public void recordResult(Driver driver, int position) {
        int points = calculatePoints(position);
        entries.add(new ResultEntry(driver, position, points));
        driver.addPoints(points);
    }

    private int calculatePoints(int position) {
        return switch (position) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            default -> 0;
        };
    }

    @Override
    public String getRaceName() {
        return name + " (" + location + ")";
    }

    @Override
    public List<String> getResults() {
        entries.sort(Comparator.comparingInt(e -> e.position));
        List<String> resultList = new ArrayList<>();
        for (ResultEntry e : entries) {
            resultList.add("  Position " + e.position + ": " + e.driver.getName() + " - " + e.points + " points");
        }
        return resultList;
    }
}
