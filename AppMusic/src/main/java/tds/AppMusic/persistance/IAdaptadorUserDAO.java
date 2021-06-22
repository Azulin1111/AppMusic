package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.users.User;

import java.util.*;

public interface IAdaptadorUserDAO {
    void storeUser(User user);

    void deleteUser(User user);

    void setUser(User user);

    User getUser(int code);

    List<User> getAllUsers();


}
