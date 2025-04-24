package Streaming_Service_Binge_Mode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private List<Episode> shuffled;
    private int position = 0;

    public ShuffleSeasonIterator(List<Episode> episodes) {
        this.shuffled = new ArrayList<>(episodes);
        Collections.shuffle(shuffled, new Random(42)); // фиксированный seed
    }

    @Override
    public boolean hasNext() {
        return position < shuffled.size();
    }

    @Override
    public Episode next() {
        return shuffled.get(position++);
    }
}