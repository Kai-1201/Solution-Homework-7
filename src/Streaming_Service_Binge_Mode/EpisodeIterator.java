package Streaming_Service_Binge_Mode;

public interface EpisodeIterator {
    boolean hasNext();
    Episode next();
}