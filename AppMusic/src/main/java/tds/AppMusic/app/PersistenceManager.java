package tds.AppMusic.app;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class PersistenceManager {

    private static final String TYPE_USER = "User";
    private static final String TYPE_SONG = "Song";

    private static final String TYPE_USER_USERNAME = "Username";
    private static final String TYPE_USER_PASSWORD = "Password";
    private static final String TYPE_USER_NAME = "Name";
    private static final String TYPE_USER_SURNAMES = "Surnames";
    private static final String TYPE_USER_EMAIL = "email";
    private static final String TYPE_USER_BIRTHDAY = "birthday";

    private static final String TYPE_SONG_NAME = "Name";
    private static final String TYPE_SONG_PATH = "Path";

    private final ServicioPersistencia sp;

    public PersistenceManager() {
        sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    public int storeUser(String username, String password, String name, String surnames, String email, Date birthday) {
        Entidad user = new Entidad();
        user.setNombre(TYPE_USER);

        int id = user.getId();
        user.setId(id);

        // Properties
        Propiedad p1 = new Propiedad(),
                  p2 = new Propiedad(),
                  p3 = new Propiedad(),
                  p4 = new Propiedad(),
                  p5 = new Propiedad(),
                  p6 = new Propiedad();

        p1.setNombre(TYPE_USER_USERNAME);
        p1.setValor(username);

        p2.setNombre(TYPE_USER_PASSWORD);
        p2.setValor(password);

        p3.setNombre(TYPE_USER_NAME);
        p3.setValor(name);

        p4.setNombre(TYPE_USER_SURNAMES);
        p4.setValor(surnames);

        p5.setNombre(TYPE_USER_EMAIL);
        p5.setValor(email);

        p6.setNombre(TYPE_USER_BIRTHDAY);
        p6.setValor(birthday.toString());

        List<Propiedad> propiedades = new LinkedList<>();
        Collections.addAll(propiedades, p1, p2, p3, p4, p5, p6);

        user.setPropiedades(propiedades);

        sp.registrarEntidad(user);

        return id;
    }

    public boolean login(String username, String password) {
        Entidad user = findEntity(TYPE_USER, TYPE_USER_USERNAME, username);
        if (user == null) return false;

        for (Propiedad p : user.getPropiedades())
            if (p.getNombre().equals(TYPE_USER_PASSWORD)) {
                return p.getValor().equals(password);
            }
        return false;
    }

    public boolean findUser(String username) {
        return findEntity(TYPE_USER, TYPE_USER_USERNAME, username) != null;
    }

    public void removeUser(String username) {
        removeEntity(TYPE_USER, TYPE_USER_USERNAME, username);
    }

    public int storeSong(String songName, URI path) {
        Entidad song = new Entidad();
        song.setNombre(TYPE_SONG);
        int id = song.getId();
        song.setId(id);

        Propiedad p1 = new Propiedad(), p2 = new Propiedad();

        p1.setNombre(TYPE_SONG_NAME);
        p1.setValor(songName);

        p2.setNombre(TYPE_SONG_PATH);
        p2.setValor(path.getPath());

        List<Propiedad> propiedades = new LinkedList<>();
        Collections.addAll(propiedades, p1, p2);

        song.setPropiedades(propiedades);

        sp.registrarEntidad(song);
        return id;
    }

    public URI getSong(String songName) {
        Entidad song = findEntity(TYPE_SONG, TYPE_SONG_NAME, songName);
        if (song == null) return null;
        for (Propiedad p : song.getPropiedades())
            if (p.getNombre().equals(TYPE_SONG_PATH)) {
                try { return new URI(p.getValor()); }
                catch (URISyntaxException ex) { return null; }
            }
        return null;
    }

    public List<String> getSongs() {
        return sp.recuperarEntidades().stream().filter(e -> e.getNombre().equals(TYPE_SONG)).map(e -> {
            for (Propiedad p : e.getPropiedades())
                if (p.getNombre().equals(TYPE_SONG_NAME))
                    return p.getValor();
                return null;
        }).collect(Collectors.toList());
    }

    public void removeSong(String songName) {
        removeEntity(TYPE_SONG, TYPE_SONG_NAME, songName);
    }


    private void removeEntity(String entityType, String propertyType, String propertyName) {
        Collection<Entidad> es = sp.recuperarEntidades();
        for (Entidad e : es)
            if (e.getNombre().equals(entityType)) {
                if (e.getPropiedades().stream().filter(p -> p.getNombre().equals(propertyType)).anyMatch(p -> p.getValor().equals(propertyName))) {
                    sp.borrarEntidad(e);
                    return;
                }
            }
    }

    private Entidad findEntity(String entityType, String propertyType, String propertyName) {
        for (Entidad e : sp.recuperarEntidades())
            if (e.getNombre().equals(entityType))
                for (Propiedad p : e.getPropiedades())
                    if (p.getNombre().equals(propertyType) && p.getValor().equals(propertyName))
                        return e;
        return null;
    }
}
