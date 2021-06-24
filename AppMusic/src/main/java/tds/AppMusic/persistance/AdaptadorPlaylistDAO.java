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
                        new Propiedad(TYPE_PLAYLIST_IS_RECENT, Boolean.toString(playlist instanceof PlaylistRecentSongs)), // True en caso de que sea una PlaylistRecentSongs
                        new Propiedad(TYPE_PLAYLIST_NAME, playlist.getName(),
                        new Propiedad(TYPE_PLAYLIST_SONGS, getCodesFromSongs(playlist.getSongs())))
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

    private String getCodesFromSongs(List<Song> songs){
        String aux = "";
        for (Song s : songs) {
            aux += s.getCode() + " ";
        }
        return aux.trim();
    }

    private List<Song> getSongsFromCodes(String songs) {

        List<Venta> listaVentas = new LinkedList<Venta>();
        StringTokenizer strTok = new StringTokenizer(ventas, " ");
        AdaptadorVentaTDS adaptadorV = AdaptadorVentaTDS.getUnicaInstancia();
        while (strTok.hasMoreTokens()) {
            listaVentas.add(adaptadorV.recuperarVenta(Integer.valueOf((String) strTok.nextElement())));
        }
        return listaVentas;
    }
}
