package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.users.User;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.*;

public enum AdaptadorUserDAO implements IAdaptadorUserDAO{
    INSTANCE;
    private final ServicioPersistencia sp;

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


    private AdaptadorUserDAO(){
        sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    /**
     * Almacena un usuario en la base de datos.
     * @param username El nombre de usuario.
     * @param password La contraseña en texto plano.
     * @param name El nombre real del usuario.
     * @param surnames Los apellidos del usuario.
     * @param email El correo electrónico del usuario
     * @param birthday La fecha de cumpleaños del usuario.
     * @return el identificador del usuario.
     */


    public void storeUser(User user) {
        Entidad eUser;
        // Si ya está registrado, no se registra de nuevo
        boolean exist = true;
        try{
            eUser = sp.recuperarEntidad(user.getCode());
        } catch(NullPointerException e){
            existe = false;
        }
        if(existe) return;

        // Registrar primero los atributos que son objetos
        AdaptadorPlaylistDAO adapterPlaylist = AdaptadorPlaylistDAO.INSTANCE;
        for(Playlist p : user.getPlaylists()){
            adapterPlaylist.storePlaylist(p);
        }

        // Crear entidad User
        eUser = new Entidad();
        eUser.setNombre(TYPE_USER);
        eUser.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(
                        new Propiedad(TYPE_USER_NAME, user.getName()),
                        new Propiedad(TYPE_USER_USERNAME, user.getNickname()),
                        new Propiedad(TYPE_USER_PREMIUM, user.isPremium()),
                        new Propiedad(TYPE_USER_PASSWORD, user.getPassword()),
                        new Propiedad(TYPE_USER_EMAIL, user.getEmail()),
                        new Propiedad(TYPE_USER_BIRTHDAY, user.getBirthday().toString()),
                        new Propiedad(TYPE_USER_PLAYLISTS, getCodesPlaylists(user.getPlaylists()))


                )
        ));


    }


    /**
     * Elimina un usuario de la base de datos. En caso de no existir el usuario, el método no hace nada.
     * @param username El nombre del usuario.
     */
    public void deleteUser(User user){

    }

    public void setUser(User user){

    }

    public User getUser(int ){}


    private String getCodesPlaylists(List<Playlist> playlists){

    }



}
