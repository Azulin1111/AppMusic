package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.PlaylistRecentSongs;
import tds.AppMusic.model.music.Song;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum AdaptadorPlaylistDAO implements IAdaptadorPlaylistDAO {
    INSTANCE;
    private final ServicioPersistencia sp;

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.

    private static final String TYPE_PLAYLIST = "Playlist";

    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";


    private AdaptadorUserDAO(){
        sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    @Override
    public void storePlaylist(Playlist playlist) {
        Entidad ePlaylist;
        boolean existe = true;

        // Si la entidad está registrada no la registra de nuevo
        try{
            ePlaylist = sp.recuperarEntidad(playlist.getCode());
        }catch (NullPointerException e) {
            existe = false;
        }
        if (existe) return;

        // Registrar primero los atributos que son objetos
        AdaptadorSongDAO adaptadorSong = AdaptadorSongDAO.INSTANCE;
        for (Song s : playlist.getSongs())
            adaptadorSong.storeSong(s);

        // Crear entidad Playlist
        ePlaylist = new Entidad();
        ePlaylist.setNombre(TYPE_PLAYLIST);
        ePlaylist.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(
                        new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(playlist instanceof PlaylistRecentSongs)),
                        new Propiedad(TYPE_PLAYLIST_NAME, )
                )
        ));



    };

    @Override
    public void deletePlaylist(Playlist playlist){};

    @Override
    public void setPlaylist(Playlist playlist){};

    @Override
    public Playlist getPlaylist(int code){return null;};

    @Override
    public List<Playlist> getAllPlaylists(){
        return null;
    };
}
