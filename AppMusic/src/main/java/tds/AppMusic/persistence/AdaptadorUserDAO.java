/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.time.Instant;
import java.util.*;

/**
 * Implementación de {@link IAdaptadorUserDAO}.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum AdaptadorUserDAO implements IAdaptadorUserDAO {
    INSTANCE;

    private static final ServicioPersistencia SP = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.
    private static final String TYPE_USER = "User";
    private static final String TYPE_USER_USERNAME = "Username";
    private static final String TYPE_USER_SURNAMES = "Surnames";
    private static final String TYPE_USER_PASSWORD = "Password";
    private static final String TYPE_USER_NAME = "Name";
    private static final String TYPE_USER_EMAIL = "Email";
    private static final String TYPE_USER_BIRTHDAY = "Birthday";
    private static final String TYPE_USER_PREMIUM = "Premium";
    private static final String TYPE_USER_PLAYLISTS = "Playlists";
    private static final String TYPE_USER_RECENTSONGS = "Recents";

    @Override
    public void storeUser(User user) {
        Entidad eUser;
        // Si ya está registrado, no se registra de nuevo
        if (SP.recuperarEntidad(user.getCode()) != null) return;

        // Registrar primero los atributos que son objetos
        AdaptadorPlaylistDAO adapterPlaylist = AdaptadorPlaylistDAO.INSTANCE;
        for(Playlist p : user.getPlaylists()){
            adapterPlaylist.storePlaylist(p);
        }

        adapterPlaylist.storePlaylist(user.getRecentPlaylist());


        // Crear entidad User
        eUser = new Entidad();
        eUser.setNombre(TYPE_USER);
        eUser.setPropiedades(new ArrayList<>(
                Arrays.asList(
                        new Propiedad(TYPE_USER_NAME, user.getName()),
                        new Propiedad(TYPE_USER_SURNAMES, user.getSurnames()),
                        new Propiedad(TYPE_USER_USERNAME, user.getNickname()),
                        new Propiedad(TYPE_USER_PREMIUM, Boolean.toString(user.isPremium())),
                        new Propiedad(TYPE_USER_PASSWORD, user.getPassword()),
                        new Propiedad(TYPE_USER_EMAIL, user.getEmail()),
                        new Propiedad(TYPE_USER_BIRTHDAY, parse(user.getBirthday())),
                        new Propiedad(TYPE_USER_PLAYLISTS, getCodesFromPlaylists(user.getPlaylists())),
                        new Propiedad(TYPE_USER_RECENTSONGS, Integer.toString(user.getCodeRecentSongs()))
                )
        ));

        // Registrar entidad User
        eUser = SP.registrarEntidad(eUser);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        user.setCode(eUser.getId());
    }

    @Override
    public void deleteUser(User user) {
        // Se borran las playlists del usuario y la de recientes
        Entidad eUser = SP.recuperarEntidad(user.getCode());
        if(eUser == null) return;

        List<Playlist> playlists = user.getPlaylists();
        Playlist recents = user.getRecentPlaylist();

        Entidad ePlaylist;
        for (Playlist p : playlists){
            ePlaylist = SP.recuperarEntidad(p.getCode());
            SP.borrarEntidad(ePlaylist);
        }

        ePlaylist = SP.recuperarEntidad(recents.getCode());
        SP.borrarEntidad(ePlaylist);

        // Se borra el usuario
        SP.borrarEntidad(eUser);

    }

    @Override
    public void setUser(User user) {
        // Se obtiene el usuario almacenado en memoria
        Entidad eUser = SP.recuperarEntidad(user.getCode());
        if(eUser == null) return;

        // Hay que obtener la lista de playlist, comparar las actuales con las nuevas y borrar o añadir playlists en función de esto.
        IAdaptadorPlaylistDAO pdao = FactoryDAO.getInstance(DAOFactories.TDS).getPlaylistDAO();
        List<Playlist> oldPlaylists = getPlaylistsFromCodes(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS));
        List<Playlist> newPlaylists = user.getPlaylists();

        // Si hay una nueva playlist, hay que añadirla a memoria
        for(Playlist newPlaylist : newPlaylists)
            if(! oldPlaylists.contains(newPlaylist))
                pdao.storePlaylist(newPlaylist);

        // Si hay una playlist que ya no forma parte del usuario, hay que eliminarla de memoria
        for(Playlist oldPlaylist : oldPlaylists)
            if (! newPlaylists.contains(oldPlaylist))
                pdao.deletePlaylist(oldPlaylist);

        eUser.getPropiedades().forEach(p -> {
                    switch (p.getNombre()) {
                        case TYPE_USER_NAME:
                            p.setValor(user.getName());
                            break;
                        case TYPE_USER_SURNAMES:
                            p.setValor(user.getSurnames());
                            break;
                        case TYPE_USER_USERNAME:
                            p.setValor(user.getNickname());
                            break;
                        case TYPE_USER_EMAIL:
                            p.setValor(user.getEmail());
                            break;
                        case TYPE_USER_PASSWORD:
                            p.setValor(user.getPassword());
                            break;
                        case TYPE_USER_PREMIUM:
                            p.setValor(Boolean.toString(user.isPremium()));
                            break;
                        case TYPE_USER_BIRTHDAY:
                            p.setValor(parse(user.getBirthday()));
                            break;
                        case TYPE_USER_PLAYLISTS:
                            String ids = getCodesFromPlaylists(user.getPlaylists());
                            p.setValor(ids);
                            break;
                        case TYPE_USER_RECENTSONGS:
                            p.setValor(Integer.toString(user.getCodeRecentSongs()));
                    }
                });

        SP.modificarEntidad(eUser);
    }

    @Override
    public User getUser(int code) {
        // Se recupera de la base de datos
        Entidad eUser;
        String name;
        String usernames;
        String nickname;
        boolean premium;
        String password;
        String email;
        Date birthday;
        List<Playlist> playlists;
        Playlist recentSongs;

        // Recuperar entidad
        eUser = SP.recuperarEntidad(code);
        if (eUser == null)  // Si no existe, devuelve null
            return null;

        // Recuperar propiedades que no son objetos
        name = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_NAME);
        usernames = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_SURNAMES);
        nickname = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_USERNAME);
        premium = Boolean.parseBoolean(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PREMIUM));
        password = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PASSWORD);
        email = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_EMAIL);
        birthday = parse(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY));

        User user = new User(name, usernames, nickname, premium, password, email, birthday);
        user.setCode(code);

        // Recuperar propiedades que son objetos
        playlists = getPlaylistsFromCodes(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS));
        for (Playlist p : playlists)
            user.addPlaylist(p);

        int recentCode = Integer.parseInt(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS));
        recentSongs = AdaptadorPlaylistDAO.INSTANCE.getPlaylist(recentCode);
        if (recentSongs != null) {
            user.setCodeRecent(recentCode);
            List<Song> recSongs = recentSongs.getSongs();
            for(Song s : recSongs)
                user.addRecentSong(s);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers(){
        List<Entidad> eUsers = SP.recuperarEntidades(TYPE_USER);
        List<User> users = new LinkedList<>();

        for (Entidad eUser : eUsers) {
            users.add(getUser(eUser.getId()));
        }
        return users;
    }


    private String parse(Date d) {
        return d.toInstant().toString();
    }

    private Date parse(String s) {
        return Date.from(Instant.parse(s));
    }


    private String getCodesFromPlaylists(List<Playlist> playlists) {
        StringBuilder aux = new StringBuilder();
        for(Playlist p : playlists){
            aux.append(p.getCode()).append(" ");
        }
        return aux.toString().trim();
    }

    private List<Playlist> getPlaylistsFromCodes(String playlists) {
        List<Playlist> listPlaylists = new LinkedList<>();
        StringTokenizer strTok = new StringTokenizer(playlists, " ");
        AdaptadorPlaylistDAO adaptadorPlaylist = AdaptadorPlaylistDAO.INSTANCE;
        while (strTok.hasMoreTokens()) {
            listPlaylists.add(adaptadorPlaylist.getPlaylist(Integer.parseInt((String) strTok.nextElement())));
        }
        return listPlaylists;
    }
}
