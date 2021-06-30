/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

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
