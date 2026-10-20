/*
 * Worked example on the Practice page: a playlist.
 * Step 5: test in main, including edge cases.
 *
 * Run (inside examples/playlist):
 *   javac *.java
 *   java PlaylistApp
 */
public class PlaylistApp {
    public static void main(String[] args) {
        Playlist list = new Playlist();
        System.out.println(list.getLongest());  // edge case: empty playlist
        list.addSong(new Song("Song A", 210));
        list.addSong(new Song("Song B", 185));
        list.addSong(new Song("Song C", 70));
        int total = list.getTotalSeconds();
        System.out.printf("Total: %d:%02d%n", total / 60, total % 60);  // %02d: 7:05, not 7:5
        System.out.println("Longest: " + list.getLongest().getTitle());
    }
}
