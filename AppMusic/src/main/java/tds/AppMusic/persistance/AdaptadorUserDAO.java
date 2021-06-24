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
    private static final ServicioPersistencia SP = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();

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


    /**
     * Elimina un usuario de la base de datos. En caso de no existir el usuario, el método no hace nada.
     * @param user El usuario.
     */
    public void deleteUser(User user) {
        // Se borran las playlists del usuario y la de recientes
        Entidad eUser = SP.recuperarEntidad(user.getCode());

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

    public void setUser(User user) {
        Entidad eUser = SP.recuperarEntidad(user.getCode());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_NAME);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_NAME, user.getName());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_USERNAME);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_USERNAME, user.getNickname());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_PREMIUM);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_PREMIUM, Boolean.toString(user.isPremium()));

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_PASSWORD);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_PASSWORD, user.getPassword());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_EMAIL);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_EMAIL, user.getEmail());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY, user.getBirthday().toString());

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS, getCodesFromPlaylists(user.getPlaylists()));

        SP.eliminarPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS);
        SP.anadirPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS, Integer.toString(user.getCodeRecentSongs()));

        SP.modificarEntidad(eUser);
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
        eUser = SP.recuperarEntidad(code);

        // Recuperar propiedades que no son objetos
        name = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_NAME);
        nickname = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_USERNAME);
        premium = Boolean.parseBoolean(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PREMIUM));
        password = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PASSWORD);
        email = SP.recuperarPropiedadEntidad(eUser, TYPE_USER_EMAIL);
        birthday = parse(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_BIRTHDAY));

        User user = new User(name, nickname, premium, password, email, birthday);
        user.setCode(code);

        // Se introduce user en el pool antes de llamar a otros adaptadores
        PoolDAO.INSTANCE.addObject(code, user);

        // Recuperar propiedades que son objetos
        playlists = getPlaylistsFromCodes(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_PLAYLISTS));
        for (Playlist p : playlists)
            user.addPlaylist(p);

        recentSongs = AdaptadorPlaylistDAO.INSTANCE.getPlaylist(Integer.parseInt(SP.recuperarPropiedadEntidad(eUser, TYPE_USER_RECENTSONGS)));
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
        List<Entidad> eUsers = SP.recuperarEntidades(TYPE_USER);
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
