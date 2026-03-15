package main;

import java.util.List;

public interface RaceResult {
    void recordResult(Driver driver, int position);
    String getRaceName();
    List<String> getResults();
}