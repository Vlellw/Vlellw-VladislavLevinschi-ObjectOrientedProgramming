package com.example.soccerteammanager;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SoccerIterator<T extends SoccerEntity> implements Iterator<T> {

    private final List<T> list;
    private int currentIndex = 0;

    public SoccerIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return list.get(currentIndex++);
    }
}