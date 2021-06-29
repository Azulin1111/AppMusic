package tds.AppMusic.model.users;


import tds.AppMusic.model.music.Song;
import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorUserDAO;

import java.util.*;

public enum UserRepository {
    INSTANCE;

    private static final Map<Integer, User> USERS = new HashMap<Integer, User>();
    private static final IAdaptadorUserDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();

    static {
        List<User> listUsers = DAO.getAllUsers();
        listUsers.forEach(u -> USERS.put(u.getCode(), u));
    }

    public void storeUser(User user){
        USERS.put(user.getCode(), user);
        DAO.storeUser(user);
    };

    public void deleteUser(User user){
        USERS.remove(user.getCode());
        DAO.deleteUser(user);
    };

    public void setUser(User user){
        USERS.put(user.getCode(), user);
        DAO.setUser(user);
    };

    public User getUser(int code){
        return USERS.get(code);
    };

    public List<User> getAllUsers(){
        return (List<User>) USERS.values();
    };

}
