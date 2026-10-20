/*
 * Worked example on the Practice page: a playlist.
 * A song has a title and a length in seconds.
 */
public class Song {
    private String title;
    private int seconds;

    public Song(String title, int seconds) {
        this.title = title;
        this.seconds = seconds;
    }

    public String getTitle() {
        return title;
    }

    public int getSeconds() {
        return seconds;
    }
}
