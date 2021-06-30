/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.persistence;

import beans.Entidad;
import beans.Propiedad;
import tds.appMusic.model.music.Playlist;
import tds.appMusic.model.music.PlaylistRecentSongs;
import tds.appMusic.model.music.Song;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.*;

/**
 * Implementación de {@link IAdaptadorPlaylistDAO}.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum AdaptadorPlaylistDAO implements IAdaptadorPlaylistDAO {
    INSTANCE;


    private static final ServicioPersistencia SP = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.
    private static final String TYPE_PLAYLIST = "Playlist";
    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";

    @Override
    public void storePlaylist(Playlist playlist) {
        Entidad ePlaylist;
        if (SP.recuperarEntidad(playlist.getCode()) != null) return;

        // Crear entidad Playlist
        ePlaylist = new Entidad();
        ePlaylist.setNombre(TYPE_PLAYLIST);
        ePlaylist.setPropiedades(new ArrayList<>(
                Arrays.asList(
                        new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(playlist instanceof PlaylistRecentSongs)), // True en caso de que sea una PlaylistRecentSongs
                        new Propiedad(TYPE_PLAYLIST_NAME, playlist.getName()),
                        new Propiedad(TYPE_PLAYLIST_SONGS, getCodesFromSongs(playlist.getSongs()))
                )
        ));

        // Registrar entidad Playlist
        ePlaylist = SP.registrarEntidad(ePlaylist);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        playlist.setCode(ePlaylist.getId());
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        // No hay que borrar las canciones
        Entidad ePlaylist = SP.recuperarEntidad(playlist.getCode());
        if(ePlaylist == null) return;

        // Se borra la playlist
        SP.borrarEntidad(ePlaylist);

    }

    @Override
    public void setPlaylist(Playlist playlist) {
        Entidad ePlaylist = SP.recuperarEntidad(playlist.getCode());
        if(ePlaylist == null) return;

        ePlaylist.getPropiedades().forEach(p -> {
            switch (p.getNombre()) {
                case TYPE_PLAYLIST_IS_RECENT:
                    p.setValor(Boolean.toString(playlist instanceof PlaylistRecentSongs));
                    break;
                case TYPE_PLAYLIST_NAME:
                    p.setValor(playlist.getName());
                    break;
                case TYPE_PLAYLIST_SONGS:
                    p.setValor(getCodesFromSongs(playlist.getSongs()));
            }
        });

        SP.modificarEntidad(ePlaylist);
    }

    @Override
    public Playlist getPlaylist(int code) {
        // Se recupera de la base de datos
        Entidad ePlaylist;
        boolean isRecent;
        String name;
        List<Song> songs;

        // Recuperar entidad
        ePlaylist = SP.recuperarEntidad(code);
        if(ePlaylist == null) return null;

        // Recuperar propiedades que no son objetos
        isRecent = Boolean.parseBoolean(SP.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_IS_RECENT));
        name = SP.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_NAME);

        Playlist playlist;
        if (isRecent)
            playlist = new PlaylistRecentSongs(name);
         else
             playlist = new Playlist(name);
        playlist.setCode(code);

        // Recuperar propiedades que son objetos
        songs = getSongsFromCodes(SP.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_SONGS));
        for (Song s : songs)
            playlist.addSong(s);

        return playlist;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Entidad> ePlaylists = SP.recuperarEntidades(TYPE_PLAYLIST);
        List<Playlist> playlists = new LinkedList<>();

        for (Entidad ePlaylist : ePlaylists) {
            playlists.add(getPlaylist(ePlaylist.getId()));
        }
        return playlists;
    }

    private String getCodesFromSongs(List<Song> songs) {
        StringBuilder aux = new StringBuilder();
        for (Song s : songs) {
            aux.append(s.getCode()).append(" ");
        }
        return aux.toString().trim();
    }

    private List<Song> getSongsFromCodes(String songs) {
        List<Song> listSongs = new LinkedList<>();
        if (songs == null || songs.equals("")) return listSongs;
        StringTokenizer strTok = new StringTokenizer(songs, " ");
        AdaptadorSongDAO adaptadorSong = AdaptadorSongDAO.INSTANCE;
        while (strTok.hasMoreTokens()) {
            listSongs.add(adaptadorSong.getSong(Integer.parseInt((String) strTok.nextElement())));
        }
        return listSongs;
    }
}
