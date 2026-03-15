package main;

import java.util.*;

public class App {
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        RallyCar initialCar = new GravelCar("Toyota", "GR Yaris", 280);

        Driver ogier = new Driver("Sebastien Ogier", "France", initialCar);
        Driver kalle = new Driver("Kalle Rovanpera", "Finland", initialCar);
        Driver tanak = new Driver("Ott Tanak", "Estonia", initialCar);
        Driver neuville = new Driver("Thierry Neuville", "Belgium", initialCar);

        manager.registerDriver(ogier);
        manager.registerDriver(kalle);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        RallyRaceResult race1 = new RallyRaceResult("Rally Finland", "Jyväskylä");
        race1.recordResult(ogier, 1);
        race1.recordResult(tanak, 2);
        race1.recordResult(kalle, 3);
        race1.recordResult(neuville, 4);
        manager.addRaceResult(race1);

        RallyCar asphaltCar = new AsphaltCar("Toyota", "Yaris WRC", 260);
        ogier.setCar(asphaltCar);
        kalle.setCar(asphaltCar);
        tanak.setCar(asphaltCar);
        neuville.setCar(asphaltCar);

        RallyRaceResult race2 = new RallyRaceResult("Monte Carlo Rally", "Monaco");
        race2.recordResult(kalle, 1);
        race2.recordResult(neuville, 2);
        race2.recordResult(ogier, 3);
        race2.recordResult(tanak, 4);
        manager.addRaceResult(race2);

        System.out.println("===== CHAMPIONSHIP STANDINGS =====");
        List<Driver> standings = ChampionshipManager.getChampionshipStandings();
        for (int i = 0; i < standings.size(); i++) {
            Driver d = standings.get(i);
            System.out.println((i + 1) + ". " + d.getName() + " (" + d.getCountry() + "): " + d.getTotalPoints() + " points");
        }

        System.out.println("\n===== CHAMPIONSHIP LEADER =====");
        Driver leader = ChampionshipManager.getLeadingDriver();
        System.out.println(leader.getName() + " with " + leader.getTotalPoints() + " points");

        System.out.println("\n===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total Drivers: " + ChampionshipManager.getTotalDrivers());
        System.out.println("Total Races: " + ChampionshipManager.getTotalRaces());
        System.out.println("Average Points Per Driver: " + String.format("%.2f", ChampionshipStatistics.calculateAveragePointsPerDriver()));
        System.out.println("Most Successful Country: " + ChampionshipStatistics.findMostSuccessfulCountry());
        System.out.println("Total Championship Points: " + ChampionshipManager.calculateTotalChampionshipPoints());

        System.out.println("\n===== RACE RESULTS =====");
        for (RaceResult race : ChampionshipManager.getRaceResults()) {
            System.out.println("Race: " + race.getRaceName());
            for (String line : race.getResults()) {
                System.out.println(line);
            }
            System.out.println();
        }

        System.out.println("===== CAR PERFORMANCE RATINGS =====");
        GravelCar g = new GravelCar("Example", "Gravel", 250);
        AsphaltCar a = new AsphaltCar("Example", "Asphalt", 250);
        System.out.println("Gravel Car Performance: " + g.calculatePerformance());
        System.out.println("Asphalt Car Performance: " + a.calculatePerformance());
    }
}
