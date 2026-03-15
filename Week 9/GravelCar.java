package main;

public class GravelCar extends RallyCar {
    public GravelCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }

    @Override
    public double calculatePerformance() {
        return horsepower * 1.694; // уникальный алгоритм для гравия
    }
}
