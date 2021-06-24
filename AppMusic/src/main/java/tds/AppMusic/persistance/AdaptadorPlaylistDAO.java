package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.PlaylistRecentSongs;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.*;


public enum AdaptadorPlaylistDAO implements IAdaptadorPlaylistDAO {
    INSTANCE;
    private final ServicioPersistencia sp;

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.

    private static final String TYPE_PLAYLIST = "Playlist";

    private static final String TYPE_PLAYLIST_IS_RECENT = "IsRecent";
    private static final String TYPE_PLAYLIST_NAME = "Name";
    private static final String TYPE_PLAYLIST_SONGS = "Songs";


    private AdaptadorPlaylistDAO(){
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
                        new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(playlist instanceof PlaylistRecentSongs)), // True en caso de que sea una PlaylistRecentSongs
                        new Propiedad(TYPE_PLAYLIST_NAME, playlist.getName()),
                        new Propiedad(TYPE_PLAYLIST_SONGS, getCodesFromSongs(playlist.getSongs()))
                )
        ));

        // Registrar entidad Playlist
        ePlaylist = sp.registrarEntidad(ePlaylist);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        playlist.setCode(ePlaylist.getId());
    };

    @Override
    public void deletePlaylist(Playlist playlist){
        // No hay que borrar las canciones
        Entidad ePlaylist = sp.recuperarEntidad(playlist.getCode());

        // Se borra la playlist
        sp.borrarEntidad(ePlaylist);

    };

    @Override
    public void setPlaylist(Playlist playlist){
        Entidad ePlaylist = sp.recuperarEntidad(playlist.getCode());

        sp.eliminarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_IS_RECENT);
        sp.anadirPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_IS_RECENT, Boolean.toString(playlist instanceof PlaylistRecentSongs));

        sp.eliminarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_NAME);
        sp.anadirPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_NAME, playlist.getName());

        sp.eliminarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_SONGS);
        sp.anadirPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_SONGS, getCodesFromSongs(playlist.getSongs()));
    };

    @Override
    public Playlist getPlaylist(int code){

        // Si la entidad está en el pool la devuelve directamente
        if (PoolDAO.INSTANCE.contains(code))
            return (Playlist) PoolDAO.INSTANCE.getObject(code);

        // Si no está en el pool, se recupera de la base de datos
        Entidad ePlaylist;
        boolean isRecent;
        String name;
        List<Song> songs;

        // Recuperar entidad
        ePlaylist = sp.recuperarEntidad(code);

        // Recuperar propiedades que no son objetos
        isRecent = Boolean.parseBoolean(sp.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_IS_RECENT));
        name = sp.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_NAME);

        Playlist playlist;
        if (isRecent)
            playlist = new PlaylistRecentSongs(name);
         else
             playlist = new Playlist(name);
        playlist.setCode(code);

        // Se introduce playlist en el pool antes de llamar a otros adaptadores
        PoolDAO.INSTANCE.addObject(code, playlist);

        // Recuperar propiedades que son objetos
        songs = getSongsFromCodes(sp.recuperarPropiedadEntidad(ePlaylist, TYPE_PLAYLIST_SONGS));
        for (Song s : songs)
            playlist.addSong(s);

        return playlist;
    };

    @Override
    public List<Playlist> getAllPlaylists(){
        List<Entidad> ePlaylists = sp.recuperarEntidades(TYPE_PLAYLIST);
        List<Playlist> playlists = new LinkedList<Playlist>();

        for (Entidad ePlaylist : ePlaylists) {
            playlists.add(getPlaylist(ePlaylist.getId()));
        }
        return playlists;
    };

    private String getCodesFromSongs(List<Song> songs){
        String aux = "";
        for (Song s : songs) {
            aux += s.getCode() + " ";
        }
        return aux.trim();
    }

    private List<Song> getSongsFromCodes(String songs) {

        List<Song> listSongs = new LinkedList<Song>();
        StringTokenizer strTok = new StringTokenizer(songs, " ");
        AdaptadorSongDAO adaptadorSong = AdaptadorSongDAO.INSTANCE;
        while (strTok.hasMoreTokens()) {
            listSongs.add(adaptadorSong.getSong(Integer.parseInt((String) strTok.nextElement())));
        }
        return listSongs;
    }
}
