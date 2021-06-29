package tds.AppMusic.persistence;

import tds.AppMusic.model.users.User;

import java.util.*;

public interface IAdaptadorUserDAO {
    void storeUser(User user);

    void deleteUser(User user);

    void setUser(User user);

    User getUser(int code);

    List<User> getAllUsers();


}
