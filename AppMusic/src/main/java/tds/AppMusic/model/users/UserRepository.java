package tds.AppMusic.model.users;


import tds.AppMusic.persistance.DAOFactories;
import tds.AppMusic.persistance.FactoryDAO;
import tds.AppMusic.persistance.IAdaptadorUserDAO;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public enum UserRepository {
    INSTANCE;

    private static final List<User> USERS = new LinkedList<>();
    private static final IAdaptadorUserDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();

    static {
        USERS.addAll(DAO.getAllUsers());
    }

    public List<User> getUsers(){
        return Collections.unmodifiableList(USERS);
    }

    public void addUser(String name, String nickname, String password, String email, Date birthday) {
        addUser(name, nickname, false, password, email, birthday);
    }

    public void addUser(String name, String nickname, boolean premium, String password, String email, Date birthday) {
        User user = new User(name, nickname, premium, password, email, birthday);
        USERS.add(user);
        DAO.storeUser(user);
    }

    public void removeUser(User user) {
        USERS.remove(user);
        DAO.deleteUser(user);
    }

}
