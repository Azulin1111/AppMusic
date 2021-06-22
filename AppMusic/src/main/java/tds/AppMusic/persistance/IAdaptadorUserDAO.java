package tds.AppMusic.persistance;

import beans.Entidad;
import beans.Propiedad;
import tds.AppMusic.model.music.Playlist;
import tds.AppMusic.model.users.User;

import java.util.*;

public interface IAdaptadorUserDAO {
    public void storeUser(User user) {}

    public void deleteUser(User user){}

    public void setUser(User user){}

    public User getUser(int code){}

    public List<User> getAllUsers{}


}
