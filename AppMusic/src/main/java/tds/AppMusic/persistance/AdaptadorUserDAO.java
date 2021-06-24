package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.music.Song;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public enum AdaptadorUserDAO implements IAdaptadorUserDAO {
    INSTANCE;
    private static final ServicioPersistencia sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

    // Los tipos descritos a continuación corresponden con los nombres de campos utilizados en la base de datos. Si
    // es necesario cambiarlos, se debe tener en cuenta que las entradas antiguas no se reconocerán con valores nuevos.

    private static final String TYPE_USER = "User";

    private static final String TYPE_USER_USERNAME = "Username";
    private static final String TYPE_USER_PASSWORD = "Password";
    private static final String TYPE_USER_NAME = "Name";
    private static final String TYPE_USER_EMAIL = "Email";
    private static final String TYPE_USER_BIRTHDAY = "Birthday";
    private static final String TYPE_USER_PREMIUM = "Premium";
    private static final String TYPE_USER_PLAYLISTS = "Playlists";
    private static final String TYPE_USER_RECENTSONGS = "Recents";

    private static final DateFormat formatter = new SimpleDateFormat();


    public void storeUser(User user) {
        Entidad eUser;
        // Si ya está registrado, no se registra de nuevo
        try {
            sp.recuperarEntidad(user.getCode());
            return;
        } catch(NullPointerException ignored) { }

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
        eUser = sp.registrarEntidad(eUser);
        // La base de datos da un identificador único
        // Se usa el que genera el servicio de persistencia
        user.setCode(eUser.getId());
    }


    /**
     * Elimina un usuario de la base de datos. En caso de no existir el usuario, el método no hace nada.
     * @param user El usuario.
     */
    public void deleteUser(User user) {
        // Se borran las playlists del usuario y la de recientes
        Entidad eUser = sp.recuperarEntidad(user.getCode());

        List<Playlist> playlists = user.getPlaylists();
        Playlist recents = user.getRecentPlaylist();

        Entidad ePlaylist;
        for (Playlist p : playlists){
            ePlaylist = sp.recuperarEntidad(p.getCode());
            sp.borrarEntidad(ePlaylist);
        }

        ePlaylist = sp.recuperarEntidad(recents.getCode());
        sp.borrarEntidad(ePlaylist);

        // Se borra el usuario
        sp.borrarEntidad(eUser);

    }

    public void setUser(User user) {
        Entidad eUser = sp.recuperarEntidad(user.getCode());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_NAME);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_NAME, user.getName());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_USERNAME);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_USERNAME, user.getNickname());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_PREMIUM);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_PREMIUM, Boolean.toString(user.isPremium()));

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_PASSWORD);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_PASSWORD, user.getPassword());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_EMAIL);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_EMAIL, user.getEmail());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY, user.getBirthday().toString());

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS, getCodesFromPlaylists(user.getPlaylists()));

        sp.eliminarPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS);
        sp.anadirPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS, Integer.toString(user.getCodeRecentSongs()));
    }

    public User getUser(int code) {
        // Si la entidad está en el pool la devuelve directamente
        if (PoolDAO.INSTANCE.contains(code))
            return (User) PoolDAO.INSTANCE.getObject(code);

        // Si no está en el pool, se recupera de la base de datos
        Entidad eUser;
        String name;
        String nickname;
        boolean premium;
        String password;
        String email;
        Date birthday;
        List<Playlist> playlists;
        Playlist recentSongs;

        // Recuperar entidad
        eUser = sp.recuperarEntidad(code);

        // Recuperar propiedades que no son objetos
        name = sp.recuperarPropiedadEntidad(eUser, TYPE_USER_NAME);
        nickname = sp.recuperarPropiedadEntidad(eUser, TYPE_USER_USERNAME);
        premium = Boolean.parseBoolean(sp.recuperarPropiedadEntidad(eUser, TYPE_USER_PREMIUM));
        password = sp.recuperarPropiedadEntidad(eUser, TYPE_USER_PASSWORD);
        email = sp.recuperarPropiedadEntidad(eUser, TYPE_USER_EMAIL);
        birthday = parse(sp.recuperarPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY));

        User user = new User(name, nickname, premium, password, email, birthday);
        user.setCode(code);

        // Se introduce user en el pool antes de llamar a otros adaptadores
        PoolDAO.INSTANCE.addObject(code, user);

        // Recuperar propiedades que son objetos
        playlists = getPlaylistsFromCodes(sp.recuperarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS));
        for (Playlist p : playlists)
            user.addPlaylist(p);

        recentSongs = AdaptadorPlaylistDAO.INSTANCE.getPlaylist(Integer.parseInt(sp.recuperarPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS)));
        if (recentSongs != null) {
            List<Song> recSongs = recentSongs.getSongs();
            for(Song s : recSongs)
                user.addRecentSong(s);
        }

        return user;
    }

    private String parse(Date d) {
        return formatter.format(d);
    }

    private Date parse(String s) {
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    public List<User> getAllUsers(){
        List<Entidad> eUsers = sp.recuperarEntidades(TYPE_USER);
        List<User> users = new LinkedList<>();

        for (Entidad eUser : eUsers) {
            users.add(getUser(eUser.getId()));
        }
        return users;
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
