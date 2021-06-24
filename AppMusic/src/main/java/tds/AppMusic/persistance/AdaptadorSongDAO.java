package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Genre;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public enum AdaptadorSongDAO implements IAdaptadorSongDAO{
    INSTANCE;
    private static final ServicioPersistencia sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.

    private static final String TYPE_SONG = "Song";

    private static final String TYPE_SONG_NAME = "Name";
    private static final String TYPE_SONG_GENRE = "Genre";
    private static final String TYPE_SONG_PATH = "Path";
    private static final String TYPE_SONG_SINGER = "Singer";
    private static final String TYPE_SONG_PLAYCOUNT = "Playcount";


    @Override
    public void storeSong(Song song) {
        Entidad eSong;
        // Si ya está registrado, no se registra de nuevo
        try {
            sp.recuperarEntidad(song.getCode());
            return;
        } catch(NullPointerException ignored) { }


        // Crear entidad Song
        eSong = new Entidad();
        eSong.setNombre(TYPE_SONG);
        eSong.setPropiedades(new ArrayList<>(
                Arrays.asList(
                        new Propiedad(TYPE_SONG_NAME, song.getName()),
                        new Propiedad(TYPE_SONG_GENRE, song.getGenre().name()),
                        new Propiedad(TYPE_SONG_PATH, song.getPath().toString()),
                        new Propiedad(TYPE_SONG_SINGER, song.getSinger()),
                        new Propiedad(TYPE_SONG_PLAYCOUNT, Integer.toString(song.getPlayCount()))
                )
        ));

        // Registrar entidad Song
        eSong = sp.registrarEntidad(eSong);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        song.setCode(eSong.getId());
    };

    @Override
    public void deleteSong(Song song){
        Entidad eSong = sp.recuperarEntidad(song.getCode());

        // Se borra la canción
        sp.borrarEntidad(eSong);
    };

    @Override
    public void setSong(Song song){
        Entidad eSong = sp.recuperarEntidad(song.getCode());

        sp.eliminarPropiedadEntidad(eSong, TYPE_SONG_NAME);
        sp.anadirPropiedadEntidad(eSong, TYPE_SONG_NAME, song.getName());

        sp.eliminarPropiedadEntidad(eSong, TYPE_SONG_GENRE);
        sp.anadirPropiedadEntidad(eSong, TYPE_SONG_GENRE, song.getGenre().name());

        sp.eliminarPropiedadEntidad(eSong, TYPE_SONG_PATH);
        sp.anadirPropiedadEntidad(eSong, TYPE_SONG_PATH, song.getPath().toString());

        sp.eliminarPropiedadEntidad(eSong, TYPE_SONG_SINGER);
        sp.anadirPropiedadEntidad(eSong, TYPE_SONG_SINGER, song.getSinger());

        sp.eliminarPropiedadEntidad(eSong, TYPE_SONG_PLAYCOUNT);
        sp.anadirPropiedadEntidad(eSong, TYPE_SONG_PLAYCOUNT, Integer.toString(song.getPlayCount()));
    };

    @Override
    public Song getSong(int code){
        // Si la entidad está en el pool la devuelve directamente
        if (PoolDAO.INSTANCE.contains(code))
            return (Song) PoolDAO.INSTANCE.getObject(code);

        // Si no está en el pool, se recupera de la base de datos
        Entidad eSong;
        String name;
        Genre genre;
        URI path;
        String singer;
        int playCount;
        int id;

        // Recuperar entidad
        eSong = sp.recuperarEntidad(code);

        // Recuperar propiedades que no son objetos
        name = sp.recuperarPropiedadEntidad(eSong, TYPE_SONG_NAME);
        genre = Genre.valueOf(sp.recuperarPropiedadEntidad(eSong, TYPE_SONG_GENRE));
        try {
            path = new URI(sp.recuperarPropiedadEntidad(eSong, TYPE_SONG_PATH));
        } catch (URISyntaxException e) {
            path = null;
        }
        singer = sp.recuperarPropiedadEntidad(eSong, TYPE_SONG_SINGER);
        playCount = Integer.parseInt(sp.recuperarPropiedadEntidad(eSong, TYPE_SONG_PLAYCOUNT));


        Song song = new Song(name, singer, genre, path, playCount);
        song.setCode(code);

        // Se introduce song en el pool
        PoolDAO.INSTANCE.addObject(code, song);

        return song;
    };

    @Override
    public List<Song> getAllSongs(){
        List<Entidad> eSongs = sp.recuperarEntidades(TYPE_SONG);
        List<Song> songs = new LinkedList<Song>();

        for (Entidad eSong : eSongs) {
            songs.add(getSong(eSong.getId()));
        }
        return songs;
    };

}
