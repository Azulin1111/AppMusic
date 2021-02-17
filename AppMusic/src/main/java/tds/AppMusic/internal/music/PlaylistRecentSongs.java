package tds.AppMusic.internal.music;

import java.util.List;

public class PlaylistRecentSongs extends Playlist{
    public PlaylistRecentSongs(String name) {
        super(name);
    }

    @Override
    public boolean addSong(Song song) {
        List<Song> songs = songs();
        songs.add(0, song);
        if(songs.size()==11){
            songs.remove(10);
        }
        return true;
    }
}
