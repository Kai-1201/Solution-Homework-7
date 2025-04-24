package Streaming_Service_Binge_Mode;

import java.util.Iterator;
import java.util.List;

public class SeasonIterator implements EpisodeIterator, Iterator<Episode> {
    private final List<Episode> episodes;
    private int position = 0;

    public SeasonIterator(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public boolean hasNext() {
        return position < episodes.size();
    }

    @Override
    public Episode next() {
        return episodes.get(position++);
    }
}