/*
 * Worked example on the Practice page: a playlist.
 * A playlist holds up to 10 songs.
 */
public class Playlist {
    private Song[] songs = new Song[10];
    private int count = 0;

    public boolean addSong(Song song) {
        if (count == songs.length) {
            return false;  // full
        }
        songs[count] = song;
        count++;
        return true;
    }

    public int getTotalSeconds() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += songs[i].getSeconds();
        }
        return total;
    }

    public Song getLongest() {
        if (count == 0) {
            return null;  // no songs yet
        }
        Song longest = songs[0];
        for (int i = 1; i < count; i++) {
            Song s = songs[i];
            if (s.getSeconds() > longest.getSeconds()) {
                longest = s;
            }
        }
        return longest;
    }
}
