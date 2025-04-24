package Streaming_Service_Binge_Mode;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private final List<Season> seasons;
    private int currentSeason = 0;
    private EpisodeIterator currentIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasons = new ArrayList<>(seasons);
        advanceToNextValidIterator();
    }

    private void advanceToNextValidIterator() {
        while (currentSeason < seasons.size()) {
            EpisodeIterator iterator = seasons.get(currentSeason).episodeIterator();
            if (iterator.hasNext()) {
                currentIterator = iterator;
                return;
            }
            currentSeason++;
        }
        currentIterator = null;
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null) return false;
        if (currentIterator.hasNext()) return true;

        currentSeason++;
        advanceToNextValidIterator();
        return currentIterator != null && currentIterator.hasNext();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return currentIterator.next();
    }
}