package tds.AppMusic.model.users;


import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorUserDAO;

import java.util.*;

public enum UserRepository {
    INSTANCE;

    private static final Map<Integer, User> USERS;
    private static final IAdaptadorUserDAO DAO;

    static {
        USERS = new HashMap<>();
        DAO = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();
        List<User> listUsers = DAO.getAllUsers();
        listUsers.forEach(u -> USERS.put(u.getCode(), u));
    }

    public void storeUser(User user){
        DAO.storeUser(user);
        USERS.put(user.getCode(), user);
    }

    public void deleteUser(User user){
        DAO.deleteUser(user);
        USERS.remove(user.getCode());
    }

    public void setUser(User user){
        DAO.setUser(user);
        USERS.put(user.getCode(), user);
    }

    public User getUser(int code){
        User user = USERS.get(code);
        if (user == null) user = DAO.getUser(code);
        return user;
    };

    public List<User> getAllUsers(){
        return new LinkedList<>(USERS.values());
    }

}
