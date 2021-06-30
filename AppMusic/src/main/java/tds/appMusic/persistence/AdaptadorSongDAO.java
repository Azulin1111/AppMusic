/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

import beans.Entidad;
import beans.Propiedad;
import tds.appMusic.model.music.Song;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementación de {@link IAdaptadorSongDAO}.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum AdaptadorSongDAO implements IAdaptadorSongDAO {
    INSTANCE;

    private static final ServicioPersistencia SP = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

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
        if (SP.recuperarEntidad(song.getCode()) != null) return;

        // Crear entidad Song
        eSong = new Entidad();
        eSong.setNombre(TYPE_SONG);
        eSong.setPropiedades(new ArrayList<>(
                Arrays.asList(
                        new Propiedad(TYPE_SONG_NAME, song.getName()),
                        new Propiedad(TYPE_SONG_GENRE, song.getGenre()),
                        new Propiedad(TYPE_SONG_PATH, song.getPath().toString()),
                        new Propiedad(TYPE_SONG_SINGER, song.getSinger()),
                        new Propiedad(TYPE_SONG_PLAYCOUNT, Integer.toString(song.getPlayCount()))
                )
        ));

        // Registrar entidad Song
        eSong = SP.registrarEntidad(eSong);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        song.setCode(eSong.getId());
    }

    @Override
    public void deleteSong(Song song){
        Entidad eSong = SP.recuperarEntidad(song.getCode());
        if(eSong == null) return;

        // Se borra la canción
        SP.borrarEntidad(eSong);
    }

    @Override
    public void setSong(Song song){
        Entidad eSong = SP.recuperarEntidad(song.getCode());
        if(eSong == null) return;

        eSong.getPropiedades().forEach(p -> {
            switch (p.getNombre()) {
                case TYPE_SONG_NAME:
                    p.setValor(song.getName());
                    break;
                case TYPE_SONG_GENRE:
                    p.setValor(song.getGenre());
                    break;
                case TYPE_SONG_PATH:
                    p.setValor(song.getPath().toString());
                    break;
                case TYPE_SONG_SINGER:
                    p.setValor(song.getSinger());
                    break;
                case TYPE_SONG_PLAYCOUNT:
                    p.setValor(Integer.toString(song.getPlayCount()));
            }
        });

        SP.modificarEntidad(eSong);


    }

    @Override
    public Song getSong(int code){
        // Se recupera de la base de datos
        Entidad eSong;
        String name;
        String genre;
        URI path;
        String singer;
        int playCount;

        // Recuperar entidad
        eSong = SP.recuperarEntidad(code);
        if(eSong == null) return null;

        // Recuperar propiedades que no son objetos
        name = SP.recuperarPropiedadEntidad(eSong, TYPE_SONG_NAME);
        genre = SP.recuperarPropiedadEntidad(eSong, TYPE_SONG_GENRE);
        try {
            path = new URI(SP.recuperarPropiedadEntidad(eSong, TYPE_SONG_PATH));
        } catch (URISyntaxException e) {
            path = null;
        }
        singer = SP.recuperarPropiedadEntidad(eSong, TYPE_SONG_SINGER);
        playCount = Integer.parseInt(SP.recuperarPropiedadEntidad(eSong, TYPE_SONG_PLAYCOUNT));


        Song song = new Song(name, singer, genre, path, playCount);
        song.setCode(code);

        return song;
    }

    @Override
    public List<Song> getAllSongs(){
        List<Entidad> eSongs = SP.recuperarEntidades(TYPE_SONG);
        List<Song> songs = new LinkedList<>();

        for (Entidad eSong : eSongs) {
            songs.add(getSong(eSong.getId()));
        }
        return songs;
    }
}
