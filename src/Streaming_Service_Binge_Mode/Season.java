package Streaming_Service_Binge_Mode;

import java.util.*;

public class Season  {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }
}