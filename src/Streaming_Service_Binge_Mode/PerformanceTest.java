package Streaming_Service_Binge_Mode;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {
    public static void main(String[] args) {
        Series series = generateTestSeries(10_000);

        testIterator("Normal", series.bingeIterator());
        testIterator("Reverse", new ReverseSeasonIterator(series.getSeasons().get(0).getEpisodes()));
        testIterator("Shuffle", new ShuffleSeasonIterator(series.getSeasons().get(0).getEpisodes()));
    }

    private static Series generateTestSeries(int episodeCount) {
        Series series = new Series();
        Season season = new Season();
        Random rand = new Random(42);

        for (int i = 0; i < episodeCount; i++) {
            season.addEpisode(new Episode("Episode " + i, rand.nextInt(30) + 20));
        }
        series.addSeason(season);
        return series;
    }

    private static void testIterator(String name, EpisodeIterator iterator) {
        long startTime = System.nanoTime();
        int count = 0;

        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.printf("%-8s | %5d episodes | %4d ms | %s%n",
                name, count, duration, getBarChart(duration));
    }

    private static String getBarChart(long value) {
        return "=".repeat((int) (value / 10));
    }
}