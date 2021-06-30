/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.users;

import tds.AppMusic.persistence.DAOFactories;
import tds.AppMusic.persistence.FactoryDAO;
import tds.AppMusic.persistence.IAdaptadorUserDAO;

import java.util.*;

/**
 * Repositorio de usuarios.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum UserRepository {
    INSTANCE;

    private static final Map<Integer, User> USERS = new HashMap<>();
    private static final IAdaptadorUserDAO DAO = FactoryDAO.getInstance(DAOFactories.TDS).getUserDAO();

    static {
        DAO.getAllUsers().forEach(u -> USERS.put(u.getCode(), u));
    }

    /**
     * Almacena un usuario en memoria y persistencia. Si el usuario ya existe, el método no modifica nada.
     * @param user El usuario a añadir.
     */
    public void storeUser(User user){
        DAO.storeUser(user);
        USERS.putIfAbsent(user.getCode(), user);
    }

    /**
     * Elimina un usuario de memoria y persistencia. Si el usuario no existe, el método no modifica nada.
     * @param user El usuario a eliminar.
     */
    public void deleteUser(User user){
        DAO.deleteUser(user);
        USERS.remove(user.getCode());
    }

    /**
     * Modifica un usuario en memoria y persistencia. Si el usuario no existe, el método no modifica nada.
     * @param user El usuario a modificar.
     */
    public void setUser(User user){
        DAO.setUser(user);
        if (USERS.containsKey(user.getCode())) USERS.put(user.getCode(), user);
    }

    /**
     * Devuelve un usuario de memoria, o en su defecto, de persistencia.
     * @param code El código del usuario.
     * @return El usuario solicitado, o {@code null} si no existe.
     */
    public User getUser(int code){
        User user = USERS.get(code);
        return user != null ? user : DAO.getUser(code);
    };

    /**
     * Devuelve todos los usuarios disponibles en memoria.
     * @return Una lista con todos los usuarios almacenadas en memoria.
     */
    public List<User> getAllUsers(){
        return DAO.getAllUsers();
    }
}
