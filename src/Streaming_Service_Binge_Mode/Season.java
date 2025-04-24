package Streaming_Service_Binge_Mode;

import java.util.*;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        if (e == null) throw new IllegalArgumentException("Episode cannot be null");
        episodes.add(e);
    }

    @Override
    public Iterator<Episode> iterator() {
        return new SeasonIterator(episodes);
    }

    public EpisodeIterator reverseIterator() {
        return new ReverseSeasonIterator(episodes);
    }
    public EpisodeIterator episodeIterator() {
        return new SeasonIterator(episodes);
    }
    public EpisodeIterator shuffleIterator() {
        return new ShuffleSeasonIterator(episodes);
    }
    public List<Episode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }
}