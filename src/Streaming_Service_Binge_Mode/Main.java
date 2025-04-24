package Streaming_Service_Binge_Mode;

    public class Main {
        public static void main(String[] args) {
            Series series = new Series();

            Season season1 = new Season();
            season1.addEpisode(new Episode("Pilot", 45));
            season1.addEpisode(new Episode("Second Episode", 42));

            Season season2 = new Season();
            season2.addEpisode(new Episode("Season 2 Premiere", 47));
            season2.addEpisode(new Episode("Season 2 Finale", 50));

            series.addSeason(season1);
            series.addSeason(season2);

            System.out.println("Normal order:");
            for (Episode e : season1) {
                System.out.println(e.getTitle());
            }

            System.out.println("\nReverse order:");
            EpisodeIterator reverse = season1.reverseIterator();
            while (reverse.hasNext()) {
                System.out.println(reverse.next().getTitle());
            }

            System.out.println("\nShuffled order:");
            EpisodeIterator shuffle = season1.shuffleIterator();
            while (shuffle.hasNext()) {
                System.out.println(shuffle.next().getTitle());
            }

            System.out.println("\nBinge watch whole series:");
            EpisodeIterator binge = series.bingeIterator();
            while (binge.hasNext()) {
                System.out.println(binge.next().getTitle());
            }
        }
    }
