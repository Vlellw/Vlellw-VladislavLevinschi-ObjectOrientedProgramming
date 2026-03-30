package com.example.soccerteammanager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Iterator;

public class Repository<T extends SoccerEntity> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public List<T> filter(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    public void sort(Comparator<T> comparator) {
        items.sort(comparator);
    }

    public Iterator<T> getIterator() {
        return new SoccerIterator<>(items);
    }

}