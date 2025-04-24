package Streaming_Service_Binge_Mode;

import java.util.*;

public class Series {
    private List<Season> seasons = new ArrayList<>();

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public EpisodeIterator bingeIterator() {
        return new BingeIterator(seasons);
    }
    public List<Season> getSeasons() {
        return Collections.unmodifiableList(seasons);
    }
}