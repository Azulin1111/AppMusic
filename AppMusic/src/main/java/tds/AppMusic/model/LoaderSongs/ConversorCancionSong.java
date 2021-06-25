import tds.AppMusic.model.music.Song;

import java.net.URI;

public class ConversorCancionSong{
    public ConversorCancionSong(){
    }

    public List<Song> convertCancionesToSong(Canciones canciones){
        List<Song> songs = new LinkedList<Song>();

        String nameSong;
        String singer;
        String genre;
        URI path;
        Song song;
        for (Cancion cancion : canciones){
            nameSong = cancion.getTitulo();
            singer = cancion.getInterprete();
            genre = cancion.getEstilo();
            path = URI.create(cancion.getURL());
            song = new Song(nameSong, singer, genre, path);
            songs.add(song);
        }

        return songs;
    }

}